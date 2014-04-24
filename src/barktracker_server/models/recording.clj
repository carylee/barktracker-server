(ns barktracker-server.models.recording
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "resources/db/barks.db"})

(defn create-recordings-table []
  (jdbc/db-do-commands db-spec (jdbc/create-table-ddl :recordings
                                  [:start :timestamp]
                                  [:stop :timestamp])))

(defn add!
  ([start]
   (jdbc/insert! db-spec :recordings {:start start}))
  ([start stop]
   (jdbc/insert! db-spec :recordings {:start start :stop stop})))

(defn barks [{start :start stop :stop}]
  (jdbc/query db-spec ["SELECT * FROM barks WHERE start BETWEEN ? AND ?" start stop]))

(defn all-recordings []
  (map #(assoc % :barks (barks %)) (jdbc/query db-spec ["SELECT * FROM recordings"])))
