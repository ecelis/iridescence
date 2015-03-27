(defproject adapter-mirth "0.1.0-SNAPSHOT"
  :description "Mirth Connect adapter for Iridescence Smart Connector"
  :url "http://example.com/FIXME"
  :license {:name "GNU Affero Public General License version"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.mirth.commons.encryption/mirth-crypto "1"]
                 [com.mirth.connect/mirth-donkey "3.2.1svn"]
                 [com.mirth.connect/mirth-server "3.2.1svn"]
                 [com.mirth.connect/mirth-client "3.2.1svn"]]
  :plugins [[lein-localrepo "0.5.3"]]
  :main ^:skip-aot adapter-mirth.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
