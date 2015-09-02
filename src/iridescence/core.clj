(ns iridescence.core
  (:require [clojure.java.io :as io]
            [me.raynes.fs :as fs])
  (:use [taoensso.timbre :only [trace debug info warn error fatal]])
  )

(def savedir "/tmp/smartconnector/") ; TODO Set a definitive path
(def wsdir (str savedir "workspace/"))
(def wipdir (str savedir "wip/"))
(def outdir (str savedir "out/"))
(def tpldir (str savedir "templates/"))

(defn gen-uuid "Generate UUID" []
  (info (str (java.util.UUID/randomUUID))))

(defn ls "List files in a given path" [path]
  (map fs/base-name (fs/list-dir path)))

(defn storage-setup "Set up storage paths" []
  ; TODO this if nil are awful
  (if (.isDirectory (io/file wsdir))
    nil
    (.mkdir (java.io.File. savedir)))
  (if (.isDirectory (io/file wsdir))
    nil
    (.mkdir (java.io.File. wsdir)))
  (if (.isDirectory (io/file wipdir))
    nil
    (.mkdir (java.io.File. wipdir)))
  (if (.isDirectory (io/file outdir))
    nil
    (.mkdir (java.io.File. outdir)))
  (if (.isDirectory (io/file tpldir))
    nil
    (.mkdir (java.io.File. tpldir))))

