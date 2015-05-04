#!/bin/bash

cd deployment

(while true; do sleep 10000; done) | sudo java -jar EvidenceServer.jar fleckenroller.cs.uni-potsdam.de production_moodle competence competence2100 competenceadmin "voyager1;A" https://eportfolio.uni-potsdam.de/moodle > evidence.log &

cd ..