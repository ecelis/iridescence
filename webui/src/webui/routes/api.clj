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
            [clojure.java.io :as io]))

(defn save-workspace []
  (layout/render
    "home.html"))

(defn load-workspace []
  (layout/render "about.html"))

(defroutes api-routes
  (context "/api" []
    (POST "/" [] (save-workspace))
    (GET "/" [] (load-workspace))))
