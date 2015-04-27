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
            [clj-yaml.core :as yaml]
            [adapter-db.core :as db])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]]))

(def savedir "/tmp")

;; TODO Read http://www.luminusweb.net/docs/responses.md
;; for proper encoding reponses
(defn json-response [data & [status]]
  "Returns a proper application/json response"
  {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string data)})

(defn yaml-response [data & [status]]
  "Returns a text/plain response YAML"
  {:status (or status 200)
   :headers {"Content-Type" "text/plain; charset=utf-8"}
   :body data})

(defn save-workspace "Saves a YAML representation of a workspace" [workspace]
  (info "Saving workspace")
  ; TODO handle nil or invalid data for spit
  ; TODO Do the yaml conversion in a functional way
  (def yaml-workspace (yaml/generate-string
    {:workspace (json/parse-string
             (get workspace :meta) true)
     :artifacts (map
             #(json/parse-string % true)
             (get workspace :data))}))
  (spit (str savedir "/workspace.sav") yaml-workspace); TODO dynamic filename
  (info yaml-workspace)
  (yaml-response yaml-workspace)) ; TODO Send apropriate response

(defn load-workspace []
  "Loads a workspace from YAML storage"
  (yaml-response "load-workspace stub"))

(defn update-workspace [id]
  "Update workspace in YAML storage"
  (yaml-response (str "update-workspace stub " id)))

(defn delete-workspace [id]
  "Delete workspace from YAML storage"
  (yaml-response (str "delete-workspace stub " id)))

(defn run-workspace "Run workspace" [id]
  (db/build-select "ta")
  (yaml-response (str "run-workspace stub " id)))

(defn try-url "Test adapter url" [url]
  ; TODO test any type of data source
  (db/test-url url)
  (yaml-response "FIX RESPONSE test-url"))

(defn get-objects "Fetch data source objects" [url]
  (db/get-tables url))

;; API Definition
;;
;; Everything in the /api context is a workspace
;; http://localhost:3000/api
;;
;; POST     /               save-workspace to YAML
;; GET      /               load-workspace from YAML
;; PUT      /:id            update-workspace (existing) in YAML
;; DELETE   /:id            delete-workspace (existing) in YAML
;; GET      /run/:id        run-workspace by ID from YAML
;; GET      /adapter/test   test-url of adapter

(defroutes api-routes
  (context "/api" []
    (POST "/" [__anti-forgery-token workspace] (save-workspace workspace))
    (GET "/" [] (load-workspace))
    (PUT "/:id" [id]
         (update-workspace id))
    (DELETE "/:id" [id]
          (delete-workspace id))
    (GET "/run/:id" [id]
         (run-workspace id))
    )
  (context "/api/adapter" []
    (GET "/test/" [__anti-forgery-token url] (try-url url))
    (GET "/object/" [__anti-forgery-token url] (get-objects url))))
