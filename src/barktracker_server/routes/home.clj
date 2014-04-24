(ns barktracker-server.routes.home
  (:require [compojure.core :refer :all]
            [barktracker-server.models.bark :as bark]
            [barktracker-server.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defn add-bark [pad length start]
  (bark/add! pad length start))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/barks" [pad length start] (add-bark pad length start)))
