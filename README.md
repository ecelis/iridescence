##### Iridescence

# Smart Connector

This is a work in progress, some/many things might be broken or need
manual intervention to build/run. Please refer to README.md files within
each submodule directory for details. Modules can change name, split or
be deleted until we reach the first stable and fully functional version.

  * webui         - SmartConnector Web User Interface
  * adapter-db    - DB Adapter (PostgreSQL, MySQL, SQL Server, Oracle)
  * adapter-mirth - Mirth Connect Adapter (WIP)
  * CirrusLoad    - Cirrus Adapter        (WIP)
  * hl7yaml       - HL7/YAML parser module (LIMBO) <-might be deleted


## Installation

Easier way to get up and running is by fetching the jar file from
http://maven.v3ctor.club/mx/com/ecaresoft/smartconnector/, run it and
browse to http://127.0.0.1:3000

    wget http://maven.v3ctor.club/mx/com/ecaresoft/smartconnector/smartconnector-latest.jar
    java -jar smartconnector-latest.jar

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

Build the Adapters first, order does not matter.

    git clone https://github.com/ecelis/iridescence.git
    cd iridescence
    for i in adapter-*; do cd $i; lein clean; lein deps; lein install; cd ..; done

I everything goes well run the standalone web server.

    cd webui
    lein clean ; lein deps
    lein ring server


Features
--------

  * Based on ideas scraped from Yahoo Pipes, Scratch and TIBCO BusinessWorks (TM)
  * Workspace is a set of Adapters for assorted data sources
    * Most of the screen is used by the workspace
    * To the right there is the interactive area for D&D objects
    * Properties Panel is bottom
      - Edits properties for adapters and connections in the workspace
  * Adapter connects to a data source
    * An URL is the format to connect to any data source
    * While editing the adapter properties it tests the connection and
      when successful fetches the data structures from it
  * Connectors define the data flow and transformations between data
    sources
  * Start and Finish adapters are automatically added to the workspace
    * One connection is added when the first connector is droped in the
      workspace
    * The Start adapter is symbolic only
    * Finish adapter can be edited

Use
---




In Progress
-----------

   * API RESTful
    * Connections Rule Engine

Planned
-------

  * Docker Container (WIP side project) [/milestones/1.0 beta]
  * Client side encryption (Browser/JavaScript)

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
EOF
