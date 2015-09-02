(ns adapter-hl7v2.core-test
  (:require [clojure.test :refer :all]
            [adapter-hl7v2.core :refer :all]))

(deftest to-json-test
  (testing "Read a file and turn to json"
    (is (= 0 1))))
