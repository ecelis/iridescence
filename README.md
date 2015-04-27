# Iridescence

Smart Connector

This is a work in progress, some/many things might be broken or need
manual intervention to build/run. Please refer to README.md files within
each submodule directory for details. Modules can change name, split or
be deleted until we reach the first stable and fully functional version.

  * webui - SmartConnector Web User Interface
  * adapter-mirth - Mirth Connect Adapter
  * CirrusLoad - Cirrus Adapter
  * hl7yaml - HL7/YAML parser module



  * Based onn Yahoo Pipes, Scratch and TIBCO BusinessWorks
  * Workspace is a set of Adapters for assorted data sources
    * Most of the screen is used by the workspace
    * To the right there is the interactive area for D&D objects
    * Properties Panel is bottom
      - Edits properties for adapters and connections in the workspace
  * Adapter connects toa data source
    * An URL is the format to connect to any data source
    * While editing the adapter propertis it tests the connection and
      when successful fetches the data structures from it
  * Connectors define the data flow and transformations between data
    sources
  * Start and Finish adapters are automatically added to the workspace
    * One connection is added when the first connector is droped in the
      workspace
    * The Start adapter is symbolic only
    * Finish adapter can be edited

In Progress
-----------

   * API RESTful
    * Connections Rule Engine

Planed
------

  * Contenedor Docker (Actualmente en progreso, pero como extra)
  * Encriptación del lado del cliente (Browser/JavaScript)

Bugs
----

  * Exceptions and logging is not beign handled properly
  * Eliminar Adaptadores o Conectores esta roto completamente
  * Iterconexión de Adaptadores no esta validada
  * Varios que se escapan a la mente :/



## Installation

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
