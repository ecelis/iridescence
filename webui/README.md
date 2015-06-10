# Smart Connector Web User Interface

Drag & Drop GUI for Smart Connector.

### Features

  * Drag & Drop blocks which represent code to be built at runtime
  * Map Source data to HL7 messages for Mirth Connector by Drag & Drop
  * Save or clone mappings to be reused by other Smart Connector
    Installs


### RESTful API

    ;; API Definition
    ;;
    ;; Everything in the /api context is a workspace
    ;; http://localhost:3000/api
    ;;
    ;; POST     /             save-workspace to YAML storage
    ;; GET      /             load-workspace from YAML storage
    ;; PUT      /:id          update-workspace (existing) in YAML storage
    ;; DELETE   /:id          delete-workspace (existing) in YAML storage
    ;; GET      /run/:id      run-workspace by ID from YAML


## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen


## Running

To start a web server for the application, run:

    lein deps
    lein ring server

Create an standalone runable jar

    lein ring uberjar
    java -jar target/webui.jar

Create a war for deploying to an app server

    lein ring uberwar

Browse to http://localhost:3000/


## Bugs

  * Dragging Connection from the toolbar does not work. It still creates
    a new connection

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
