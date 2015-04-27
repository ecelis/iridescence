(defproject adapter-db "0.1.8-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [honeysql "0.5.2"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :main ^:skip-aot adapter-db.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
