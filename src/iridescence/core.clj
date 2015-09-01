(ns iridescence.core
  (:require [clojure.java.io :as io]))

(def savedir "/tmp/smartconnector/") ; TODO Set a definitive path
(def wsdir (str savedir "/workspace/"))
(def wipdir (str savedir "/wip/"))
(def outdir (str savedir "/out/"))
(def tpldir (str savedir "/templates/"))

(defn ls "List files in a given path" [path]
  (def fs (file-seq (io/file path)))
  [fs])

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

