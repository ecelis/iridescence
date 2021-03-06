(defproject adapter-db "0.1.18-SNAPSHOT"
  :description "Iridescence Database Adapter"
  :url "http://github.com/ecelis/iridescence"
  :license {:name "Affero General Public License"
            :url "http://github.com/ecelis/iridescence"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [honeysql "0.6.2"]
                 [com.taoensso/timbre "3.4.0"]
                 [com.taoensso/tower "3.0.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [mysql/mysql-connector-java "5.1.35"]
                 [fuzzy-urls "0.1.0-SNAPSHOT"]]
  :plugins [[codox "0.8.11"]]
  )
