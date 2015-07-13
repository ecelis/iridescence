(defproject adapter-db "0.1.15-SNAPSHOT"
  :description "Database Adapters"
  :url "http://github.com/ecelis/iridescence"
  :license {:name "Affero General Public License"
            :url ""}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [honeysql "0.5.2"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [mysql/mysql-connector-java "5.1.35"]
                 [fuzzy-urls "0.1.0-SNAPSHOT"]]
  :plugins [[codox "0.8.11"]]
  )
