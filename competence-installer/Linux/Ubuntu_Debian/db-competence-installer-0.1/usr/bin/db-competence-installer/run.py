#!/usr/bin/python

import os, subprocess

if os.path.isfile("evidenceserver.properties"):
    print "Starting program"
    subprocess.call(['java', '-jar', 'CompetenceServer.jar'])
else :
    print "No evidenceserver.properties"
    
