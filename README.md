# Wissensmodellierung

## Competence Server Project in Ubuntu/Debian
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


## Competence Server Project in Windows
### Install
Download the zip file or clone this project and build yourself the installer. To install this server just use "install.exe" and follow the instructions. After that you can run tray.exe. The software will start a icon in your system tray and with this menu you can run or stop the server.

### Update
Like Linux you have to use the install.exe to update your server

### Usage
Use the tray.exe. It deploys the server and let you start and stop it via a system tray icon.

### Delete
Just delete this folder. We didn't put something in your system..
_old README_
Wissensmodellierung
===================

BUILDING the project
 - install the project competence-application with: clean package -Dmaven.test.skip=true.
 - This will compile all required projects but might take a while.		

STARTING the project

[optional] java -jar CompetenceImporter [pathtoCSV] [pathtotdb]

java -jar CompetenceServer.jar [path to TDB location]

java -jar EvidenceServer.jar [ServerIp] [db-name] [dbuser] [dbpassword] [moodleadminname] [moodleadminpassword] [fullapplicationpath]
 
java -jar EvidenceServer.jar localhost bitnami_moodle root voyager https://someurl/moodle


CONFIGURING YOUR SERVER

Put these lines in your http.conf or open the ports mentioned. The ports can be changed in MagicString.java.

ProxyPass  /competence http://localhost:8084
ProxyPassReverse /competence http://localhost:8084

ProxyPass  /evidence http://localhost:8083
ProxyPassReverse /evidence http://localhost:8083

 
USING the project

deploy the moodle app as specified in the README of https://github.com/uzuzjmd/competence

--Some coding hacks you should know--

Encoding Verfahren (Stand 26.05.2014)

 - Identifier werden (alt) in CompOntologyAccess encodiert. Wenn die Formatierung des Strings zum Display wichtig ist, wird eine lesbare Form
	als Dataproperty "definition abgespeichert"
 - Die DAO-Schicht kodiert die Identifier in CompetenceOntologyDao (ähnlich zu dem alten Verfahren)
 - Fuer SingleTonDaos werden die Identifier nach dem alten Verfahren (oben) kodiert
 - Identifier dürfen nicht mit einem Sonderzeichen oder einer Zahl beginnen
 - Moodle Kurs Ids werden als "n"+Id kodiert. 

Festgelegte Strings (Stand 26.05.2014)
Prefixe, Dateinamen ... etc sind in der Klasse MagicStrings als default definiert

 	
