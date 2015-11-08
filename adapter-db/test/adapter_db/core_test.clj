(ns adapter-db.core-test
  (:require [clojure.test :refer :all]
            [adapter-db.core :as adb]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]
            ))

(def dbms (list "postgresql" "mysql"))
(def url (str (first dbms) "://127.0.0.1/northwind?user=iusr&password=1qaz"))

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

(deftest get-columns-test
  (testing "get-columns"
    (is (associative? (adb/get-columns url "usstates")))))
(deftest get-tables-test
  (testing "get-tables"
    (is (= (chunked-seq? (adb/get-tables url))))))
