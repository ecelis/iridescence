(ns adapter-db.core-test
  (:require [clojure.test :refer :all]
            [adapter-db.core :as adb]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]
            ))

(def dbms (list "postgresql" "mysql" "oracle" "mssql"))

(def sqlmap {:postgresql
             (-> (select :*)
              (from :information_schema.tables)
              (where [:not= :table_schema "pg_catalog"]
                     [:not= :table_schema "information_schema"]))
             :mysql
             (-> (select :table_name)
              (from :information_schema.tables)
              (where [:= :table_schema "northwind"]))})

(deftest tables-sqlmap-test
  (testing "tables-sqlmap"
    (is (= (get sqlmap :postgresql)
           (adb/tables-sqlmap "postgresql" "northwind"))
        (= (get sqlmap :mysql)
           (adb/tables-sqlmap "mysql" "northwind")))))
