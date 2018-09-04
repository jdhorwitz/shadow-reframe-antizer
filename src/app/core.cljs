(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.router :as router]
            [app.events]
            [app.subs]
            [app.views :as views]))

(defn ^:dev/after-load start
  []
  (r/render [views/app]
            (.getElementById js/document "app")))

(defn ^:export main
  []
  (start)
  (router/app-routes)
  (rf/dispatch-sync [:initialise-db]))
