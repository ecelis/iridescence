(ns iridescence.core
  (:require [clojure.java.io :as io]
            [me.raynes.fs :as fs]
            [clj-uuid :as uuid])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]])
  )

(def savedir "/tmp/smartconnector")
(def path {:wsdir (str savedir "/workspace")
           :wipdir (str savedir "/wip")
           :outdir (str savedir "/out")
           :tpldir (str savedir "/templates")})

(defn gen-uuid "Generate UUID" []
  (uuid/v1))

(defn ls "List files in a given path" [path]
  (file-seq (io/file path)))

(defn storage-setup "Set up storage paths" []
  ; TODO this if nil are awful
  (if (.isDirectory (io/file (get path :wsdir)))
    nil
    (.mkdir (java.io.File. (get path :savedir))))
  (if (.isDirectory (io/file (get path :wsdir)))
    nil
    (.mkdir (java.io.File. (get path :wsdir))))
  (if (.isDirectory (io/file (get path :wipdir)))
    nil
    (.mkdir (java.io.File. (get path :wipdir))))
  (if (.isDirectory (io/file (get path :outdir)))
    nil
    (.mkdir (java.io.File. (get path :outdir))))
  (if (.isDirectory (io/file (get path :tpldir)))
    nil
    (.mkdir (java.io.File. (get path :tpldir)))))

