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

(defn get-recording [id]
  (first (map #(assoc % :barks (barks %)) (jdbc/query db-spec ["SELECT * FROM recordings WHERE rowid=?" id]))))

(defn all-recordings []
  (map #(assoc % :barks (barks %)) (jdbc/query db-spec ["SELECT * FROM recordings"])))

(defn bark-in-range? [{bark-start-time :start :as bark} window-start window-size]
  (and
    (>= bark-start-time window-start)
    (< bark-start-time (+ window-start window-size))))

(defn windowed-barks [window-size recording-start recording-stop barks]
  (map (fn [window-start] (filter #(bark-in-range? % window-start window-size) barks))
       (range recording-start recording-stop window-size)))

(defn bark-histogram [window-size recording-start recording-stop barks]
  (map count (windowed-barks window-size recording-start recording-stop barks)))

;(defn merge-barks [all-previous {current-pad :pad current-length :length :as current}]
;  (let [{previous-start :start previous-stop :stop :as previous} (first all-previous)]
;    (cond
;      (nil? previous) (reverse (cons current all-previous))
;      (< (- current-start previous-stop) MERGE-THRESHOLD) (cons {:start previous-start :stop current-stop} (rest all-previous))
;      :else (cons current all-previous))))

;(defn merge-recording [recording]
  ;(reverse (reduce merge-barks '() recording)))

