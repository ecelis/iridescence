# Smart Connector

## User Manual

- Build from source
- Install
- Quick Start


### Build from source

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

If everything goes well run the standalone application and browse to
http://127.0.0.1:3000

    java -jar ~/iridescence/webui/target/webui.jar


`build.sh` by default cleans and build Smart Connector, it also takes
the `clean` sub-command, to clean only.


### Install

#### Install on JBoss

TODO


### Quick Start


