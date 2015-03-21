(ns hl7yaml.core
  (:gen-class)
  (:require
    [clj-yaml.core :as yaml]
    [com.nervestaple.hl7-parser.parser :as hl7parser]
    [com.nervestaple.hl7-parser.message :as hl7message]))
;; Read the message from a file, just like unix everything is a file!
(def hl7message (slurp "../data/data.hl7")) ; TODO make a funtion 


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
