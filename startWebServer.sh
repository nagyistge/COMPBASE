#!/bin/sh
cd competence-webapp/uzuzjmd.competence.gui
export DISPLAY=:0.0
(while true; do sleep 10000; done) | gwt:run > gwt.log &

cd ../..
