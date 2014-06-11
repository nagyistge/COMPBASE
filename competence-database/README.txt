BUILDING the project
 - install the project competence-application  		   
 
STARTING the project

unzip tdbstarter.zip

java -jar CompetenceServer.jar [path to TDB location]

java -jar EvidenceServer.jar [ServerIp] [db-name] [dbuser] [dbpassword] [moodleadminname] [moodleadminpassword]
 
z.B. java -jar EvidenceServer.jar localhost bitnami_moodle root voyager
 
USING the project

deploy the moodle app as specified in the README of https://github.com/uzuzjmd/competence

[Some coding hacks you should know]
Encoding Verfahren (Stand 26.05.2014)
	- Identifier werden (alt) in CompOntologyAccess encodiert. Wenn die Formatierung des Strings zum Display wichtig ist, wird eine lesbare Form
	als Dataproperty "definition abgespeichert"
	- Die DAO-Schicht kodiert die Identifier in CompetenceOntologyDao (ähnlich zu dem alten Verfahren)
	- Fuer SingleTonDaos werden die Identifier nach dem alten Verfahren (oben) kodiert
	- Identifier d�rfen nicht mit einem Sonderzeichen oder einer Zahl beginnen
	- Moodle Kurs Ids werden als "n"+Id kodiert. 

Festgelegte Strings (Stand 26.05.2014)
Prefixe, Dateinamen ... etc sind in der Klasse MagicStrings als default definiert

 	