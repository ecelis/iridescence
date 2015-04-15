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


(defproject hl7yaml "0.1.2-SNAPSHOT"
  :description "HL7 to YAML interpreter"
  :url "http://ecaresoft.com"
  :license {:name "GNU Affero General Public License"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-yaml "0.4.0"]
                 [com.taoensso/timbre "3.4.0"]
                 [clojure-hl7-parser "3.4"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
