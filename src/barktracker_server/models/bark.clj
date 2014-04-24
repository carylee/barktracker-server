(ns barktracker-server.models.bark
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "resources/db/barks.db"})

(defn create-barks-table []
  (jdbc/db-do-commands db-spec (jdbc/create-table-ddl :barks
                                  [:pad :int]
                                  [:length :int]
                                  [:start :timestamp])))

(defn add! [pad length start]
  (jdbc/insert! db-spec :barks {:pad pad :length length :start start}))
