(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :active-page
 (fn [db _]
   (:active-page db)))

(rf/reg-sub
 :counter
 (fn [db _]
  (:counter db))) 

(rf/reg-sub
 :topic-id
 (fn [db _]
  (:topic-id db)))
