Smart Connector
===============

[![Build Status](https://travis-ci.org/ecelis/iridescence.svg?branch=master)](https://travis-ci.org/ecelis/iridescence)

This is a work in progress, some/many things might be broken or need
manual intervention to build/run. Please refer to README.md files within
each submodule directory for details. Modules can change name, split or
be deleted until we reach the first stable and fully functional version.

  * webui           - SmartConnector Web User Interface
  * adapter-db      - DB Adapter (PostgreSQL, MySQL, SQL Server, Oracle)
  * adapter-mirth   - Mirth Connect Adapter (WIP)
  * adapter-cirrus  - Cirrus Adapter        (WIP)
  * adapter-hl7v2   - HL7v2 parser module (WIP)
  * adapter-csv     - CSV Adapter (WIP)

Build from source
-----------------

In order to build Smart Connector you need to install Leiningen first.
(Java 8 SDK is a requirement for both lein and Smart Connector, so it is
assumed to be already installed)

    mkdir ~/bin ; cd ~/bin
    wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
    chmod 755 lein
    ./lein
    export PATH=~/bin:$PATH
    cd ~
    git clone git@github.com:ecelis/iridescence.git
    cd iridescence
    ./build.sh

I everything goes well run the standalone application and browse to
http://127.0.0.1:3000

    java -jar ~/iridescence/webui/target/webui.jar


Features
--------

    * Adapter connects to a data source
    * An URL is the format to connect to any data source
    * While editing the adapter properties it tests the connection and
      when successful fetches the data structures from it
    * Connectors define the data flow and transformations between data
      sources


In Progress
-----------

    * API RESTful
    * Connections Rule Engine


Bugs
----

  * Exceptions and logging are not beign handled properly
  * Remove from workspace is badly broken
  * Data is not beign validated in any way (yet)
  * Several not very anoying bugs

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <ecelis@ecaresoft.com.mx>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
