(ns adapter-db.core-test
  (:require [clojure.test :refer :all]
            [adapter-db.core :refer :all]))

(def dbms ("postgres" "mysql" "oracle" "mssql"))
(defn sqlmap [dbms]
  (cond (= "postgtres")
          {:where [:and [:not= :table_schema "pg_catalog"]
                        [:not= :table_schema "information_schema"]]
           :from (:information_schema.tables),
           :select (:*)}
        (= "mysql")
          {:where [:= :table_schema "northwind"],
           :from (:information_schema.tables),
           :select (:table_name)}
        :else "Unknown DBMS type"))

(deftest tables-sqlmap-test
  (testing "tables-sqlmap"
    (is (= 1 2))))

(deftest get-columns-test
  (testing "get-columns"
    (is (= 1 2))))

(deftest get-tables-test
  (testing "get-tables"
    (is (= 1 2))))

(deftest test-url-test
  (testing "test-url"
    (is (= 1 2))))

(deftest build-select-test
  (testing "build-select"
    (is (= 1 2))))

(deftest exec-query-test
  (testing "exec-query"
    (is (= 1 2))))
