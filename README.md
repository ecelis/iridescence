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

Read the [manual](https://github.com/ecelis/iridescence/wiki)

Build from source
-----------------

In order to build Smart Connector you need to install Leiningen first.
(Java 8 SDK is a requirement for both lein and Smart Connector, so it is
assumed to be already installed)

    git clone --recursive git@github.com:ecelis/iridescence.git
    cd iridescence
    ./build.sh

If everything goes well run the standalone application and browse to
http://127.0.0.1:3000

    java -jar ~/iridescence/webui/target/iridescence_standalone.jar


`build.sh` by default cleans and build Smart Connector, it also takes
the `clean` sub-command, to clean only.

Build User Manual
-----------------

The user manual is included in the `doc` directory, which is a clone of
the github wiki for the project. It is writen in markdown and can be
rendered to ODT and HTML with `pandoc`

    cd doc
    pandoc Home.md -f markdown -t odt -s -o User-Manual.odt
    pandoc Home.md -f markdown -t html -s -o User-Manual.html


## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <ecelis@ecaresoft.com.mx>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
