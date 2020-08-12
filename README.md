java-csv
========

[![Build Status](https://travis-ci.org/solnone/java-csv.svg?branch=master)](https://travis-ci.org/solnone/java-csv)

Java-based transform <a href="http://en.wikipedia.org/wiki/Comma-separated_values">CSV</a> data into <a href="http://www.json.org">JSON</a> / <a href="http://www.w3.org/standards/xml">XML</a>

Dependencies
------------

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-text</artifactId>
    <version>1.9</version>
</dependency>
<dependency>
    <groupId>javax.json</groupId>
    <artifactId>javax.json-api</artifactId>
    <version>1.1.4</version>
</dependency>
```

Download and build
------------------

You need [Java](https://www.java.com), [git](https://git-scm.com) and [maven](https://maven.apache.org).

```bash
git clone git://github.com/solnone/java-csv.git
cd java-csv
mvn compile
mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
mvn site:site
```

Usage
-----

Invoke csv2json.sh from the command
```bash
    ./csv2json.sh < file.csv > file.json
```

Invoke csv2xml.sh from the command
```bash
    ./csv2xml.sh < file.csv > file.xml
```