(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]))

(def routes 
  ["/" {""        :home
        "about"   :about
        "counter" :counter
        "topics/" {""      :topics
                   [:topic-id] :topic}}])         

(defn- parse-url
  [url]
  (bidi/match-route routes url))

(defn- dispatch-route
  [matched-route]
  (rf/dispatch [:set-active-page (:handler matched-route)])
  (rf/dispatch [:set-topic-id (get-in matched-route [:route-params :topic-id])]))

(defn app-routes
  []
  (pushy/start! (pushy/pushy dispatch-route parse-url)))

(def url-for (partial bidi/path-for routes))