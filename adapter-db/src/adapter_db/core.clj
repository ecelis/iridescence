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
  (:gen-class))

(defn get-connection "Get connection from" [uri]
  ;TODO Maybe this should be moved to somewhere else
  jdbc/get-connection uri)

(defn build-select "Build a select from" [statement]
  (def sqlmap (map data))
  (jdbc/query (sql/format sqlmap)))

(defn get-tables "Get tables from" [db]
  (def sqlmap {:select [tables]
               :from [db]})

(defn get-columns "Get columns from" [table]
  (def sqlmap {:select [columns]
               :from [table]})
  (jdbc/query (sql/format sqlmap)))

