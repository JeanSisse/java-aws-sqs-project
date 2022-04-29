#!/bin/bash
export className=SqsProjectSenderApplication
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.jean.app.$className"