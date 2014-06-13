#!/bin/sh

cd $(dirname $0)
if [ ! -f target/classpath.txt ]; then
  mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
fi

CP="target/classes:$(cat target/classpath.txt)"
exec java -cp "${CP}" sol.csv.CSV2XML "$@"

