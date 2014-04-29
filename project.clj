(defproject barktracker-server "0.1.0-SNAPSHOT"
  :description "The server side component of an app to monitor a dog's barking"
  :url "http://www.barktracker.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.3.3"]
                 [clj-time "0.7.0"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [ring-server "0.3.1"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler barktracker-server.handler/app
         :init barktracker-server.handler/init
         :destroy barktracker-server.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
