(defproject hl7yaml "0.1.0-SNAPSHOT"
  :description "HL7 to YAML interpreter"
  :url "http://ecaresoft.com"
  :license {:name "GNU Affero General Public License"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-yaml "0.4.0"]
                 [com.taoensso/timbre "1.6.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
