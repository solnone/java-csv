java-csv
========

Java-based transform <a href="http://en.wikipedia.org/wiki/Comma-separated_values">CSV</a> data into <a href="http://www.json.org">JSON</a> / <a href="http://www.w3.org/standards/xml">XML</a>

Dependencies
------------

```xml
<dependencies>
    <dependency>
        <groupId>commons-lang</groupId>
        <artifactId>commons-lang</artifactId>
        <version>2.6</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish</groupId>
        <artifactId>javax.json</artifactId>
        <version>1.0.4</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Download and build
------------------

You need <a href="http://www.java.com">Java</a>, <a href="http://git-scm.com">git</a> and <a href="http://git-scm.com">maven</a>.

```bash
git clone git://github.com/solnone/java-csv.git
cd java-csv
mvn compile
mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt
```

Usage
-----

Invoke csv2json.sh from the command

    ./csv2json.sh < file.csv > file.json
 
Invoke csv2xml.sh from the command

    ./csv2xml.sh < file.csv > file.xml
