(defproject adapter-mirth "0.1.0-SNAPSHOT"
  :description "Mirth Connect adapter for Iridescence Smart Connector"
  :url "http://example.com/FIXME"
  :license {:name "GNU Affero Public General License version"
            :url "http://www.gnu.org/licenses/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [log4j/log4j "1.2.17"]
                 [com.mirth.commons.encryption/mirth-crypto "1"]
                 [com.thoughtworks.xstream/xstream "1.4.8"]
                 [org.apache.commons/commons-lang3 "3.3.2"]
                 [org.apache.httpcomponents/httpcore "4.4.1"]
                 [org.apache.httpcomponents/httpmime "4.4"]
                 [org.apache.httpcomponents/httpclient "4.4"]
                 [com.mirth.connect/mirth-donkey "3.2.1svn"]
                 [com.mirth.connect/mirth-server "3.2.1svn"]
                 [com.mirth.connect/mirth-client "3.2.1svn"]]
  :plugins [[lein-localrepo "0.5.3"]]
  :main ^:skip-aot adapter-mirth.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
