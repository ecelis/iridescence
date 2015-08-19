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

(defn tables-sqlmap "Depending on the DBMS in the URL build
                           an appropriate sqlmap" [dbms database]
  (cond (= "postgres" dbms)
          (-> (select :*)
              (from :information_schema.tables)
              (where [:not= :table_schema "pg_catalog"]
                     [:not= :table_schema "information_schema"]))
        (= "postgresql" dbms)
          (-> (select :*)
              (from :information_schema.tables)
              (where [:not= :table_schema "pg_catalog"]
                     [:not= :table_schema "information_schema"]))
        (= "mysql" dbms)
          (-> (select :table_name)
              (from :information_schema.tables)
              (where [:= :table_schema database]))
        :else (warn "Unknown DBMS type")))

(defn get-columns "Get columns from" [url table]
  (def dbms (:scheme (string->url url)))
  (def sqlmap (cond (= "postgres" dbms) (sql/build :select :column_name
               :from :information_schema.columns
               :where [:= :table_name table])
        (= "postgresql" dbms) (sql/build :select :column_name
               :from :information_schema.columns
               :where [:= :table_name table])
        (= "mysql" dbms) (sql/build :select :column_name
                :from :information_schema.columns
                :where [:= :table_name table])
        :else (warn "Unknown DBMS type")))
  (hash-map (keyword table) (jdbc/query url (sql/format sqlmap)
                                        :result-set-fn vec)))

(defn get-tables "Get tables from" [url]
  (def tables)
  (try ; TODO reuse it (def db-handle (jdbc/get-connection url))
      (def sqlmap (tables-sqlmap (:scheme (string->url url))
                                 (:path (string->url url))))
      (def tables (map #(get % :table_name) (jdbc/query url (sql/format sqlmap)
                                                        :result-set-fn vec)))
  (catch Exception e (fatal e)))
  (def table-defs (conj (map #(get-columns url %) tables) nil))
  (rest table-defs))

(defn test-url "Tests URL" [url]
  ; TODO ASAP retrieve other schemas along public
  (try
    ; TODO better reuse db-handle with-db-connection
    ; TODO this true/flase flag hack sucks, fix it
    (jdbc/get-connection url) (get-tables url)
    (catch Exception e (info e))))

(defn build-select "Build a select from" [url tables query]
  (def fro (map keyword (set (string/split tables #" "))))
  (def sele (vec (map keyword (set (string/split query #" ")))))
  (jdbc/query url (sql/format (sql/build :select sele
                         :from fro)) :result-set-fn vec))

(defn exec-query [url query-map]
  (jdbc/query (sql/format query-map) :result-set-fn vec))
