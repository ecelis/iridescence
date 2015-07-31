#!/bin/bash

PROJECTS="adapter-csv adapter-db adapter-hl7v2"
case $1 in
"clean")
	for dep in $PROJECTS;
	do
	  cd $dep
	  echo "$(pwd) clean..."
	  lein clean;
	  cd ..
	done
	cd webui
	lein clean;
	;;
*)
	for dep in $PROJECTS;
	do
	  cd $dep
	  pwd
	  lein clean; lein deps; lein uberjar; lein install
	  cd ..
	done
	cd webui
  echo "$(pwd) clean..."
	lein clean; lein deps; lein ring uberjar
	;;
esac

