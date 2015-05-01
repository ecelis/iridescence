;;  Iridescence Mirth Adapter
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


(defproject adapter-mirth "0.1.0-SNAPSHOT"
  :description "Mirth Connect adapter for Iridescence Smart Connector"
  :url "http://example.com/FIXME"
  :license {:name "GNU Affero Public General License version"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [log4j/log4j "1.2.17"]
                 [com.thoughtworks.xstream/xstream "1.4.8"]
                 [org.apache.commons/commons-lang3 "3.3.2"]
                 [org.apache.httpcomponents/httpcore "4.4.1"]
                 [org.apache.httpcomponents/httpmime "4.4"]
                 [org.apache.httpcomponents/httpclient "4.4"]
                 [com.mirth.commons.encryption/mirth-crypto "1"]
                 [com.mirth.connect/mirth-donkey "3.2.1svn"]
                 [com.mirth.connect/mirth-server "3.2.1svn"]
                 [com.mirth.connect/mirth-client "3.2.1svn"]]
  :plugins [[lein-localrepo "0.5.3"]
            [codox "0.8.11"]]
  :main ^:skip-aot adapter-mirth.core
  :target-path "target/%s"
  :profiles {:dev
             {:dependencies [[org.apache.derby/derby "10.11.1.1"]]}
             :uberjar {:aot :all}})
