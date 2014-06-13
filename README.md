java-csv
========

Java-based transform <a href="http://en.wikipedia.org/wiki/Comma-separated_values">CSV</a> data into JSON / XML

Download and build
==================

You need Java, git and maven.

```bash
git clone git://github.com/solnone/java-csv.git
cd java-csv
mvn compile
mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
```
