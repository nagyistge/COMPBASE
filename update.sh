#!/bin/bash
mvn pull

cd competence-application
mvn clean install -Dmaven.test.skip=true
cd ..

cd competence-database
mvn clean package -Dmaven.test.skip=true
cd ..

rm -rf deployment	
mkdir deployment

cp competence-database/target/*.jar deployment
cp competence-database/src/main/scala/resources/kompetenzen_moodle_utf8.csv deployment/standard.csv
cp competence-database/src/main/scala/resources/epos.xml deployment/epos.xml

mkdir deployment/tdb
cd deployment	
