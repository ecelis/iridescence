#!/bin/bash
cd /clojure
git clone -b ecelis https://github.com/ecelis/clojure-hl7-messaging-2-parser.git
cd clojure-hl7-messaging-2-parser
lein clean; lein deps; lein install
cd /clojure
git clone https://github.com/ecelis/clojure-fuzzy-urls.git
cd clojure-fuzzy-urls
lein clean; lein deps; lein install
cd /vagrant
./build.sh
cd webui
#lein ring server-headless
