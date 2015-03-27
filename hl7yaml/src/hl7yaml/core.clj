;;  HL7 YAMl converter
;;  Copyright (C) <year>  <name of author>
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

(ns hl7yaml.core
  (:gen-class)
  (:require
    [clj-yaml.core :as yaml]
    [com.nervestaple.hl7-parser.parser :as hl7parser]
    [com.nervestaple.hl7-parser.message :as hl7message]))
;; Read the message from a file, just like unix everything is a file!
(def rawmsg (slurp "../data/data.hl7")) ; TODO make a funtion
(def rawyaml (slurp "../data/data.yaml"))
(def s {:1 "Uno" :2 "Dos" :3 "Tres"})
(def t [:3 :2 :1])

(defn rechl7
  "HL7 message reception and parsing"
  [rawhl7]
  (hl7parser/parse rawhl7))

(defn recyaml
  "YAML message definition & configuration reception"
  [rawyaml]
  (yaml/parse-string rawyaml))

;; TODO SCRATCHPAD

(defn map-elements
  "Map elements from YAML to HL7"
  [source-map target-vector]
  (zipmap target-vector (vals source-map)))
