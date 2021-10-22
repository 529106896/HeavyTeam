#!/bin/bash --

java -Xms1g -Xmx1g -Xss256k \
     -Dcom.sun.management.jmxremote.port=3214 \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -jar target/Exp_3-0.0.1-SNAPSHOT.jar
