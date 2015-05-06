;;  Database adapters for Iridescence Smart Connector
;;  Copyright (C) 2015 eCaresoft Inc
;;  Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>
;;
;;  This file is part of Iridescence Smart Connector
;;
;;  Iridescence Smart Connector is free software: you can redistribute it
;;  under the terms of the GNU Affero General Public License as published by
;;  the Free Software Foundation, either version 3 of the License, or (at
;;  your option) any later version.
;;
;;  This program is distributed in the hope that it will be useful,
;;  but WITHOUT ANY WARRANTY; without even the implied warranty of
;;  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;;  GNU Affero General Public License for more details.
;;
;;  You should have received a copy of the GNU Affero General Public License
;;  along with this program.  If not, see <http://www.gnu.org/licenses/>.

(ns adapter-db.core
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]))

(defn get-columns "Get columns from" [url table]
  (def sqlmap (sql/build :select :column_name
               :from :information_schema.columns
               :where [:= :table_name table]))
  (hash-map (keyword table) (jdbc/query url (sql/format sqlmap)
                                        :result-set-fn vec)))

(defn get-tables "Get tables from" [url]
  (def tables nil)
  (try ; TODO reuse it (def db-handle (jdbc/get-connection url))
       (def sqlmap (sql/build :select :*
                              :from :information_schema.tables
                              :where [:= :table_schema "public"]))
        (def tables (map #(get % :table_name)
                         (jdbc/query url (sql/format sqlmap)
                                     :result-set-fn vec)))
        (catch Exception e (info e)))
  (def table-defs (conj (map #(get-columns url %) tables) nil))
  (rest table-defs))

(defn test-url "TEsts URL" [url]
  (try
    ; TODO better reuse db-handle with-db-connection
    ; TODO this true/flase flag hack sucks, fix it
    (jdbc/get-connection url) (get-tables url)
    (catch Exception e (info e))))

(defn build-select "Build a select from" [statement]
  (def sqlmap (map statement))
  (jdbc/query (sql/format sqlmap)))
