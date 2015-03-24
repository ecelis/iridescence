(ns hl7yaml.core
  (:gen-class)
  (:require
    [clj-yaml.core :as yaml]
    [com.nervestaple.hl7-parser.parser :as hl7parser]
    [com.nervestaple.hl7-parser.message :as hl7message]))
;; Read the message from a file, just like unix everything is a file!
(def rawmsg (slurp "../data/data.hl7")) ; TODO make a funtion
(def rawyaml (slurp "../data/data.yaml"))
(def s {:1 "Uno" :2 "Dos" :3 "Tres"})
(def t [:3 :2 :1])

(defn rechl7
  "HL7 message reception and parsing"
  [rawhl7]
  (hl7parser/parse rawhl7))

(defn recyaml
  "YAML message definition & configuration reception"
  [rawyaml]
  (yaml/parse-string rawyaml))

;; TODO SCRATCHPAD

(defn map-elements
  "Map elements from YAML to HL7"
  [source-map target-vector]
  (zipmap target-vector (vals source-map)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
