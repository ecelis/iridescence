;;  HL7 YAMl converter
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

(ns adapter-hl7v2.core
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]))

(defn get-columns
  "I don't do a whole lot."
  [url]
  (println url))

(defn get-tables "Doc string" [url]
  (println url))

(defn test-url "Test HL7v2 URL" [url]
  (def hl7file (slurp "/tmp/my.hl7"))
  hl7file)

(defn build-select "Build a SELECT FROM HL7v2" [url tables query]
  (println url))

(defn exec-query "Exec query against HL7v2" [url query-map]
  (println url))

