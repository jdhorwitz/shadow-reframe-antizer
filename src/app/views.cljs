(ns app.views
  (:require [re-frame.core :as rf]
            [app.router :as router]
            [antizer.reagent :as ant]))

(defn header
  []
  [:div
   [:h1 "A template for re-frame apps"]
   [ant/button {:on-click #(ant/message-info "Hello Reagent!")} "Click me"]
   [:div
    [:ul
     [:li
      [:a {:href (router/url-for :home)} "Home"]]
     [:li
      [:a {:href (router/url-for :about)} "About"]]
     [:li
      [:a {:href (router/url-for :topics)} "Topics"]]
     [:li
      [:a {:href (router/url-for :counter)} "Counter"]]]]])

(defn home
  []
  [:div "home"])

(defn about
  []
  [:div "about"])

(defn topics
  []
  [:div
   [:h2 "topics"]
   [:ol
    [:li
      [:a {:href (router/url-for :topic :topic-id "cljs")} "ClojureScript"]]
    [:li
      [:a {:href (router/url-for :topic :topic-id "clj")} "Clojure"]]]])

(defn topic
  [params]
  (let [topic-id @(rf/subscribe [:topic-id])]
   [:div
    [:h2 "topics"]
    [:ul
      [:li [:a {:href (router/url-for :topics)} "back"]]
      [:li [:p topic-id]]]]))

(defn counter
  []
  (let [counter @(rf/subscribe [:counter])]
   [:div
    [:button
     {:on-click #(rf/dispatch [:decrement])}
     "-"]
    [:button {:disabled true} counter]
    [:button
     {:on-click #(rf/dispatch [:increment])}
     "+"]]))

(defn pages [page-name params]
  (case page-name
    :home [home]
    :about [about]
    :topics [topics]
    :topic [topic params]
    :counter [counter]
    [home]))

(defn app
  []
  (let [active-page (rf/subscribe [:active-page])]
   (fn
     []
     [:div
      [header]
      [pages @active-page]])))
