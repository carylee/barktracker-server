(ns barktracker-server.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [barktracker-server.models.recording :as recording]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to barktracker-server"]
     (include-css "/css/screen.css")]
    [:body body]))

(defn div [weight]
  (let [rgb (int (- 255 (* 12.75 weight)))]
    [:div {:style (str "background:rgb(" rgb "," rgb "," rgb ");") }]))

(defn visualize [{barks :barks start :start stop :stop} window-size]
  (let [weights (recording/bark-histogram window-size start stop barks)]
    [:div {:id "vis"} (map div weights)]))
