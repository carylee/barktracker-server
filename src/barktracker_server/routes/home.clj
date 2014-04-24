(ns barktracker-server.routes.home
  (:require [compojure.core :refer :all]
            [barktracker-server.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))
