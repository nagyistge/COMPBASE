#!/bin/sh
cd competence-webapp/uzuzjmd.competence.gui
export DISPLAY=:0.0
mvn gwt:compile > gwt.log
(while true; do sleep 10000; done) | mvn gwt:run > gwt.log &

cd ../..
