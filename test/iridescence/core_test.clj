(ns iridescence.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [me.raynes.fs :as fs]
            [iridescence.core :refer :all]))

(def save_t "/tmp/smartconnector") ; TODO Set a definitive path
(def ws_t (str save_t "/workspace"))
(def wip_t (str save_t "/wip"))
(def out_t (str save_t "/out"))
(def tpl_t (str save_t "/templates"))

(deftest globals-test
  (testing "Global defs"
    (is (= savedir save_t))
    (is (= (get path :wsdir) ws_t))
    (is (= (get path :wipdir) wip_t))
    (is (= (get path :outdir) out_t))
    (is (= (get path :tpldir) tpl_t))))

(deftest storage-setup-test
  (testing "Storage setup" ; TODO Check all dirs
    (io/delete-file (io/file save_t) true)
    (storage-setup)
    (is (= (io/file savedir) (io/file save_t)))
    (is (= (io/file (get path :wsdir)) (io/file ws_t)))
    (is (= (io/file (get path :wipdir)) (io/file wip_t)))
    (is (= (io/file (get path :outdir)) (io/file out_t)))
    (is (= (io/file (get path :tpldir)) (io/file tpl_t)))
    ))

(deftest ls-test
  (testing "List files"
    (is (= (ls savedir) (file-seq (io/file save_t))))
    ))
