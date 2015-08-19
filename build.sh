#!/bin/bash

PROJECTS="adapter-csv adapter-db adapter-hl7v2"
MANUAL="User-Manual"
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
  cd ../doc ; rm *.{html,odt}
	;;
"doc")
  cd doc
  for i in "odt html"; do
    pandoc "$MANUAL.md" -f markdown -t $i -s -o "$MANUAL.$i"
  done
  ;;
"help")
  echo "$0 [clean|build|help] withouth commands default is build everything"
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
	lein clean; lein deps; lein ring uberjar; lein ring uberwar
	;;
esac

