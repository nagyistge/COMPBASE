#!/usr/bin/python

import os, subprocess

class CompetenceServer():
	def __init__(self):
		print "init Server"

	def runServer(self):
		print "run Server"
		if os.path.isfile("evidenceserver.properties"):
		    print "Starting program"
		    self.job = subprocess.Popen(['java', '-jar', 'CompetenceServer.jar'])
		    return self.job
		else :
		    print "No evidenceserver.properties"

	def stopServer(self):
		print "stop Server"
		self.job.terminate()
def main():
	server = CompetenceServer()
	job =  server.runServer()
	print job
	server.stopServer
if __name__=='__main__':
	main()
