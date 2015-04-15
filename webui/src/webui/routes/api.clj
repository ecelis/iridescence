;;  API RESTful Iridescence Smart connector
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

(ns webui.routes.api
  (:require [webui.layout :as layout]
            [compojure.core :refer :all]
            [cheshire.core :as json]
            [clojure.java.io :as io]
            [clj-yaml.core :as yaml])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]))

(def savedir "/tmp")

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string data)})

(defn yaml-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "text/plain; charset=utf-8"}
   :body data})

(defn save-workspace [workspace]
  ; TODO handle nil or invalid data for spit
  (def yaml-workspace (yaml/generate-string (json/parse-string workspace)))
  (spit (str savedir "/workspace.sav") yaml-workspace); TODO dynamic filename
  (yaml-response yaml-workspace))

(defn load-workspace []
  (json-response "hola"))

(defroutes api-routes
  (context "/api" []
    (POST "/" [__anti-forgery-token workspace] (save-workspace workspace))
    (GET "/" [] (load-workspace))))
