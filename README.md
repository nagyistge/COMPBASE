Wissensmodellierung
===================

BUILDING the project
 - install the project competence-application with: clean package -Dmaven.test.skip=true.
 - This will compile all required projects but might take a while.		

STARTING the project

java -jar CompetenceServer.jar [path to TDB location]

java -jar EvidenceServer.jar [ServerIp] [db-name] [dbuser] [dbpassword] [moodleadminname] [moodleadminpassword]
 
java -jar EvidenceServer.jar localhost bitnami_moodle root voyager


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

 	
