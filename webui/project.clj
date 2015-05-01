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


(defproject webui "0.1.3-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring-server "0.4.0"]
                 [selmer "0.8.2"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [markdown-clj "0.9.65"]
                 [environ "1.0.0"]
                 [im.chit/cronj "1.4.3"]
                 [compojure "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [ring/ring-session-timeout "0.1.0"]
                 [ring-middleware-format "0.4.0"]
                 [noir-exception "0.2.3"]
                 [bouncer "0.3.2"]
                 [prone "0.8.1"]
                 [ragtime "0.3.8"]
                 [yesql "0.5.0-rc1"]
                 [com.h2database/h2 "1.4.182"]
                 [cheshire "5.4.0"]
                 [hl7yaml "0.1.2-SNAPSHOT"]
                 [clj-yaml "0.4.0"]
                 [adapter-db "0.1.13-SNAPSHOT"]]

  :min-lein-version "2.0.0"
  :uberjar-name "webui.jar"
  :repl-options {:init-ns webui.handler}
  :jvm-opts ["-server"]

  :main webui.core

  :plugins [[lein-ring "0.9.1"]
            [lein-environ "1.0.0"]
            [lein-ancient "0.6.5"]
            [ragtime/ragtime.lein "0.3.8"]
            [codox "0.8.11"]]

  :ring {:handler webui.handler/app
         :init    webui.handler/init
         :destroy webui.handler/destroy
         :uberwar-name "webui.war"}
  
  :ragtime
  {:migrations ragtime.sql.files/migrations
   :database "jdbc:h2:./site.db"}
  
  
  
  
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
            
             :aot :all}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.3.2"]
                        [pjstadig/humane-test-output "0.7.0"]
                        ]
         :source-paths ["env/dev/clj"]
         
        
         
         :repl-options {:init-ns webui.repl}
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]
         :env {:dev true}}})
