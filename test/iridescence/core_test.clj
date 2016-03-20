(ns iridescence.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [iridescence.core :refer :all]))

(def save_t "/tmp/smartconnector") ; TODO Set a definitive path
(def ws_t (str save_t (str "/" "workspace")))
(def wip_t (str save_t (str "/" "wip")))
(def out_t (str save_t (str "/" "out")))
(def tpl_t (str save_t (str "/" "templates")))

(deftest globals-test
  (testing "Global defs"
    (is (= savedir save_t))
    (is (= wsdir ws_t))
    (is (= wipdir wip_t))
    (is (= outdir out_t))
    (is (= tpldir tpl_t))))

(deftest storage-setup-test
  (testing "Storage setup" ; TODO Check all dirs
    (io/delete-file (io/file save_t) true)
    (storage-setup)
    (is (= (io/file savedir) (io/file save_t)))
    (is (= (io/file wsdir) (io/file ws_t)))
    (is (= (io/file wipdir) (io/file wip_t)))
    (is (= (io/file outdir) (io/file out_t)))
    (is (= (io/file tpldir) (io/file tpl_t)))
    ))

(deftest ls-test
  (testing "List files"
    (is (= (ls save_t) [(file-seq (io/file save_t))]))
    ))
