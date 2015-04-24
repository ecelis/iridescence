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



  * Basado en Yahoo Pipes, Scratch y TIBCO BusinessWorks
  * El Workspace es una colección de Adaptadores
    * La mayor parte de la pantalla la ocupa el Workspace
    * A la Derecha el Drop Zone permite arrastrar plantillas y otras
      funciones relacionadas a la inter-conexión entre Adaptadores
    * Abajo el Panel de Propiedades
      - Modifica propiedades de Adaptadores, Conectores y Workspace
  * Adaptador se conecta a una fuente de datos
    * Usando la minima información posible
    * Inspecciona la fuente de datos y provee campos
  * Conector determina el flujo de datos
    * Contiene reglas de interconexón entre origenes de datos
  * Los Adaptadores de Inicio y Fin son automáticos
    * Un Conector se agrega automáticamente entre Inicio y el primer
      Adptador arrastrado al Workspace
    * El Inicio es simbólico
    * El Fin por default va al Log, pero se puede editar

En Progreso
-----------

   * API RESTful
    * Motor de Reglas de Connectores
    * Implementación de reglas bśicas de interconexión
    * Implementación de Adaptadores deribados
      - DB (PsotgreSQL, Oracle, MySQL, SQL Server)
      - Mirth Connect
      - Cirrus

Planeado
--------

  * Contenedor Docker (Actualmente en progreso, pero como extra)
  * Encriptación del lado del cliente (Browser/JavaScript)

Bugs
----

  * Eliminar Adaptadores o Conectores esta roto completamente
  * Iterconexión de Adaptadores no esta validada
  * Varios que se escapan a la mente :/



## Installation





## IGNORE EVERYTHING BELOW
    git clone git@github.com:ecelis/iridescence.git
    cd iridescence
    git submodule init
    git submodule update
    gradle build

## Usage

FIXME: explanation


## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

  * build.gradle does nothing currently, its only a template for
    subprojects

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
