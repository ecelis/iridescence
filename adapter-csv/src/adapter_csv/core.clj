;;  CSV adapters for Iridescence Smart Connector
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

(ns adapter-csv.core
  (:require [clojure.data.csv :as csv]
             [clojure.java.io :as io])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]))

(defn get-columns "Get columns from CSV" [url]
  (println url))

(defn get-tables "Get tables from CSV" [url]
  (println url))

(defn test-url "Test CSV URL" [url]
  (with-open [in-file (io/reader "/tmp/my.csv")]
  (def csv_response (doall
    (csv/read-csv in-file))))
  csv_response)


(defn build-select "Build a SELECT FROM CSV" [url tables query]
  (println url))

(defn exec-query [url query-map]
  (println url))
