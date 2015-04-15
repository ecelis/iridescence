# hl7yaml

HL7/YAML parser for Smart Connector

## Installation

`git submodule init && git submodule update` should be run in the root
directory /iridesence before trying to build with lein

    lein deps
    lein repl

    (require 'hl7yaml.core)
    (hl7yaml.core/rechl7 hl7msg)
    (hl7yaml.core/recyaml yamlmsg)



### Bugs


## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
