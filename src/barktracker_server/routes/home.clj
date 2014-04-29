(ns barktracker-server.routes.home
  (:require [compojure.core :refer :all]
            [hiccup.core :as hiccup]
            [barktracker-server.models.bark :as bark]
            [barktracker-server.models.recording :as recording]
            [barktracker-server.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defn add-bark [pad length start]
  (bark/add! pad length start))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/recording/:id" [id] (layout/common (layout/visualize (recording/get-recording id) 20000)))
  (POST "/barks" [pad length start] (add-bark pad length start)))
