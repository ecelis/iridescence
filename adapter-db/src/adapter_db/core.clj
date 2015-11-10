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
            [honeysql.helpers :refer :all]
            [clojure.string :as string]
            [fuzzy-urls.url :refer :all]
            [fuzzy-urls.lens :as lens :refer [build-url-lens]])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]
        [clojure.walk]))

(defn tables-sqlmap

  "Returns database specific map, takes dbms and database parameters.

  (tables-sql {:engine :postgresql :db \"northwind\"})"
  ;; TODO Test Oracle and MSSQL
  ;; Oracle reference
  ;; http://stackoverflow.com/questions/2247310/how-do-i-list-all-tables-in-a-schema-in-oracle-sql#2247758
  ;; MSSQL reference
  ;; https://msdn.microsoft.com/en-us/library/ms186778.aspx
  ;; http://stackoverflow.com/a/8293531/1366519

  [{engine :engine db :db}]

  (case engine
    :postgresql (-> (select :*)
                    (from :information_schema.tables)
                    (where [:not= :table_schema "pg_catalog"]
                           [:not= :table_schema "information_schema"]))
    :mysql (-> (select :table_name)
               (from :information_schema.tables)
               (where [:= :table_schema db]))
    :oracle (-> (select :table_name "OBJECT_NAME")
                (from "USER_OBJECTS")
                (where [:= "OBJECT_TYPE" "TABLE"]))
    :mssql (-> (select :table_name "Name")
               (from :sys.tables)
               (where [:= "is_ms_shipped" 0]))
    :else (warn (str "Unknown DBMS type " engine))))

(defn get-columns

  "Get columns from table given a JDBC URL

  (get-columns \"postgresql://127.0.0.1/northwind?user=myuser&password=secret\""

  [url table]

  (def sqlmap (case (keyword (:scheme (string->url url)))
              :postgresql (sql/build :select :column_name
                                     :from :information_schema.columns
                                     :where [:= :table_name table])
              :mysql (sql/build :select :column_name
                                :from :information_schema.columns
                                :where [:= :table_name table])
              :else (warn "Unknown DBMS type")))
    (hash-map (keyword table) (jdbc/query url (sql/format sqlmap)
                                        :result-set-fn vec)))

(defn get-tables

  "Get tables from given JDBC URL

  (get-tables \"postgresql://localhost:5432/northwind?user=myuser&password=secret\""

  [url]

  (def tables)
  (try ; TODO reuse it (def db-handle (jdbc/get-connection url))
      (def sqlmap (tables-sqlmap {:engine
                                  (keyword (:scheme (string->url url)))
                                  :db (:path (string->url url))}))
      (def tables (map #(get % :table_name)
                       (jdbc/query url (sql/format sqlmap)
                                   :result-set-fn vec)))
  (catch Exception e (fatal e)))
  (def table-defs (conj (map #(get-columns url %) tables) nil))

  (rest table-defs))

(defn test-url

  "Test JDBC URL connection, return nil on success.

  (test-url \"postgresql://127.0.0.1/northwind?user=myuser&password=1qaz\""

  [url]

  ; TODO ASAP retrieve other schemas along public
  (try
    ; TODO better reuse db-handle with-db-connection
    ; TODO this true/flase flag hack sucks, fix it
    (jdbc/get-connection url) nil
    (catch Exception e (warn e) {:hell "TODO proper error and success returns"})))

(defn get-query

  "Build SQL query for selected tables

  (get-query url tables)"

  [{url :url tables :tables pivot :pivot}]

  [{_ cols} (first tables)])

(defn execute-query

  "Execute a query against the data base

  (exec-query url sqlmap)"

  [{url sqlmap}]

  (jdbc/query (sql/format sqlmap) :result-set-fn vec))
