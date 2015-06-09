#!/bin/bash

for dep in adapter-csv adapter-db adapter-hl7v2;
do
  cd $dep
  pwd
  lein clean; lein deps; lein uberjar; lein install
  cd ..
done

cd webui
lein clean; lein deps; lein ring uberjar
