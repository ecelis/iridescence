# adapter-mirth

Mirth Connect Adapter for Smart Connector

## Installation

After building Mirth Connect JARs (README.mirth) install them with lein
localrepo

The Mirth Jars can be downloaded at

    wget http://maven.v3ctor.club/com/mirth/commons/encryption/mirth-crypto/1/mirth-crypto-1.jar
    wget http://maven.v3ctor.club/com/mirth/connect/mirth-donkey/3.2.1svn/mirth-donkey-3.2.1svn.jar
    wget http://maven.v3ctor.club/com/mirth/connect/mirth-server/3.2.1svn/mirth-server-3.2.1svn.jar
    wget http://maven.v3ctor.club/com/mirth/connect/mirth-client/3.2.1svn/mirth-client-3.2.1svn.jar


    lein clean
    lein deps
    lein localrepo install ../lib/mirth/mirth-donkey-3.2.1svn.jar \
      com.mirth.connect/mirth-donkey 3.2.1svn
    lein localrepo install ../lib/mirth/mirth-server-3.2.1svn.jar \
      com.mirth.connect/mirth-server 3.2.1svn
    lein localrepo install ../lib/mirth/mirth-client-3.2.1svn.jar \
      com.mirth.connect/mirth-client 3.2.1svn
    lein localrepo install ../../lib/mirth/mirth-crypto-1.jar \
      com.mirth.commons.encryption/mirth-crypto 1
    lein deps


### Examples


    lein repl
    (import [com.mirth.connect.client.core Client])
    (doto (new com.mirth.connect.client.core.Client "server"))



### Bugs

## License

Copyright © 2015 eCaresoft Inc.
Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>

Copying and distribution of this file, with or without modification, are
permitted in any medium without royalty provided the copyright notice
and this notice are preserved.  This file is offered as-is, without any
warranty.
