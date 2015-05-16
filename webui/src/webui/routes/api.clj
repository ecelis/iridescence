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

(def savedir "/home/ecelis/Projects/iridescence/data") ; TODO Set a definitive path
(def wsdir "/workspace")
(def wipdir "/wip")
(def outdir "/out")

;; TODO Read http://www.luminusweb.net/docs/responses.md
;; for proper encoding reponses
(defn json-response "Returns a proper application/json response"
  [data & [status]]
    {:status (or status 200)
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/generate-string data)})

(defn yaml-response "Returns a text/plain response YAML"
  [data & [status]]
    {:status (or status 200)
   :headers {"Content-Type" "text/plain; charset=utf-8"}
   :body data})

(defn save-workspace "Saves a YAML representation of a workspace, it however
                     returns the json representation of the workspace"
  [workspace]
  ; TODO handle nil or invalid data for spit
  ; TODO Do the yaml conversion in a functional way
  (def workspace-map {:workspace (json/parse-string
             (get workspace :meta) true)
     :artifacts (map
             #(json/parse-string % true)
             (get workspace :data))})
  (def yaml-workspace (yaml/generate-string workspace-map))
  (if (get (get workspace-map :workspace true) :draft true)
    (def save-to (str savedir wipdir "/"))
    (def save-to (str savedir wsdir "/")))
  (info save-to)
  (try (spit (str save-to (get (json/parse-string
                                           (get workspace :meta) true) :guid))
             yaml-workspace) (json-response workspace)
       (catch Exception e (info e))))

(defn load-workspace "Loads a workspace from YAML storage and returns its JSON
                     representation" [id]
  (try (json-response (yaml/parse-string (slurp (str savedir wsdir "/" id))))
       (catch Exception e (info e))))

(defn update-workspace "Update workspace in YAML storage" [id]
    (json-response {:not (str "yet implemented " id)}))

(defn delete-workspace "Delete workspace from YAML storage" [id]
  (try (io/delete-file (str savedir "/" id))
       (json-response {:action (str "deleted workspace " id)})
       (catch Exception e (info e))))

(defn run-workspace "Run workspace" [id]
  (db/build-select "ta")
  (yaml-response {:not (str "run-workspace stub " id)}))

(defn try-url "Test adapter url" [url]
  ; TODO test any type of data source
  (def res (db/test-url url))
  (if res
    (json-response {:tables res
                    })
    (json-response {:tables nil :columns nil})))

(defn get-objects "Fetch data source objects" [url]
  (info (db/get-tables url)))

;; API Definition
;;
;; Everything in the /api/ context is a workspace
;; /api/object/ is any DB, Class, File, Network connection, etc which happens
;; to be an adapter in the workspace
;; http://localhost:3000/api/
;;
;; POST     /               save-workspace to YAML
;; GET      /:id            load-workspace from YAML
;; PUT      /:id            update-workspace (existing) in YAML
;; DELETE   /:id            delete-workspace (existing) in YAML
;; GET      /run/:id        run-workspace by ID from YAML
;; GET      /adapter/test   test-url of adapter

(defroutes api-routes
  (context "/api" []
    (POST "/" [__anti-forgery-token workspace] (save-workspace workspace))
    (GET "/:id" [id] (load-workspace id))
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
