# COMPBASE 

The goal of COMBASE is the creation of a generic competence database that allows for comparison and consistent persistence of competencies. Plugins for current elearning system are being developed as well as mobile applications and badge systems.

## Quick START

### Interface Documentation
http://fleckenroller.cs.uni-potsdam.de/doku/ contains a html documentation of the relevant interfaces. 

### Interfaces Endpoints
You need to replace the prefix of the rest interfaces with either localhost:8084 or the 
the url prefix of the deployed tomcat app such as: http://localhost:8080/competence-servlet/competence (if you build locally like below) or you can use the server provided by us here: http://fleckenroller.cs.uni-potsdam.de/app/competence-database/... such as
http://fleckenroller.cs.uni-potsdam.de/app/competence-database/competences/learningtemplates.

## Install Competence Server Project in Ubuntu/Debian
### Install
This project provides a .deb file which could be easily installed via package manager. Simply use the file in the path [competence-installer/Linux/Ubuntu_Debian](https://github.com/uzuzjmd/Wissensmodellierung/tree/master/competence-installer/Linux/Ubuntu_Debian)
Further instructions will be provided via the terminal.

### Update
After the project is installed it could be that the server is getting upgraded by developers. To check if there is an update available you can execute the file /usr/bin/db-competence-installer/install.py. This little file will check if there is a new build and ask you to install it.

### Usage
To work with the installed server you have to use your service system systemd
**sudo service db-competence (start|status|stop)** without brackets and use only one key word

### Configure server attributes
In the directory /usr/bin/db-competence-installer are all files you need
 - log4j.xml - the file to configure your logger [log4j](http://logging.apache.org/log4j/2.x/). The standard configuration is that the logger logs in three ways:
	- Debug.log - logging the debug level, important for *developer*
	-	Info.log - logging the info level, important for *server admins*
	- std out - logging the info level in std out, viewable via sudo service db-competence status
 - evidenceserver.properties - the file to configure the paths for the server

### Delete
It would be a pitty if you want to delete this wonderful system. Anyway, you can use apt-get to delete the server: sudo apt-get remove --purge db-competence.
Please use purge in case you want to install this deb file again.


## Install Competence Server Project in Windows
### Requirements
Before you install this project you have to follow the following steps:

 - Install [Neo4J](http://neo4j.com/download/)
 - Add **dbms.security.auth_enabled=false** to  C:\Users\{UserName}\Documents\Neo4j\default.graphdb\neo4j.properties in order to disable authentication (in productive use you can reenable this)
	
### Install
Download the zip file here. To install this server just use "install.exe" and follow the instructions. After that you can run tray.exe. The software will start a icon in your system tray and with this menu you can run or stop the server.

### Update
You can click on the install.exe to update your server

### Usage
Use the tray.exe. It deploys the server and let you start and stop it via a system tray icon.

### Delete
Just delete this folder. We didn't put something in your system..


## Deploy as war file

- change evidenceserver.properties in competence-database-servlet\competence-servlet\src\main\webapp\WEB-INF\classes to your needed configuration

Important to change: moodleURL, neo4jURL
			
- install the project competence-application with: clean package -Dmaven.test.skip=true. This will compile all required projects but might take a while.		
- competence-database-servlet\competence-servlet\target should contain the deploy-ready war file 

## Build from sources

### Build with maven

- Increase Maven Memory:
	- export MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=128m"
	- (or on Windows:) set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=128m

- git submodule update --init --recursive
- cd competence-application
- mvn clean install, takes a while and this might lead to an OutOfMemoryException which can be solved with:
- mvn install (again)


### Build with intellij
- open project with intellij (scala plugin)
- make project: voilá!
- copy competence-database/src/test/scala/evidenceserver.properties to competence-database/target/classes and change rootPath to the project location
- Run competence-database/uzuzjmd/competence/main/RestServer.main

### Make Dokumentation

- cd competence-database
- mvn test

competence-database/target/miredot should contain the html documentation of the interfaces.











 


 	
