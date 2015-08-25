(ns webui.routes.upload
  (:require [noir.response :as resp]
            [noir.session :as session]))

(defmacro in-try-catch [& body]
  `(try
     ~@(butlast body)
     (resp/status 200 ~(last body))
     (catch Throwable t#
       (.printStackTrace t#)
       (resp/status 500 "error"))))

;(defn list-files []
;  (db/list-files))

(defn get-file [name]
  (if-let [{:keys [name type data]} (name)]
    (resp/content-type type (new java.io.ByteArrayInputStream data))
    (resp/status 404 (resp/empty))))

(defn upload-file! [file]
  (in-try-catch
   (file)))

(defn delete-file! [name]
  (in-try-catch (name)))
