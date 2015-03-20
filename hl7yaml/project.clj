(defproject hl7yaml "0.1.0-SNAPSHOT"
  :description "HL7 to YAML interpreter"
  :url "http://ecaresoft.com"
  :license {:name "GNU Affero General Public License"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojars.mpenet/clj-yaml "0.3.4"]]
  :main ^:skip-aot hl7yaml.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
