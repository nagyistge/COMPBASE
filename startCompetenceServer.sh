#!/bin/sh
cd deployment
(while true; do sleep 10000; done) | java -jar CompetenceServer.jar tdb http://fleckenroller.cs.uni-potsdam.de/app > competence.log &
cd ..

