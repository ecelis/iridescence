# adapter-mirth

FIXME: description

## Installation

After building Mirth Connect JARs install them with lein localrepo

    lein localrepo install ../lib/mirth/mirth-donkey-3.2.1svn.jar \
      com.mirth.connect/mirth-donkey 3.2.1svn
    lein localrepo install ../lib/mirth/mirth-server-3.2.1svn.jar \
      com.mirth.connect/mirth-server 3.2.1svn
    lein localrepo install ../lib/mirth/mirth-client-3.2.1svn.jar \
      com.mirth.connect/mirth-client 3.2.1svn
    lein localrepo install \
      ../../workspace.luna/Server/lib/mirth-crypto.jar \
      com.mirth.commons.encryption
    lein deps



### Bugs

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved.  This file is offered as-is,
without any warranty.
