#!/bin/bash
export className=SqsProjectConsomerApplication
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.jean.app.$className"