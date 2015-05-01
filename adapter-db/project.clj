(defproject adapter-db "0.1.13-SNAPSHOT"
  :description "Database Adapters"
  :url "http://github.com/ecelis/iridescence"
  :license {:name "Affero General Public License"
            :url ""}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [honeysql "0.5.2"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]]
  :plugins [[codox "0.8.11"]]
  )
