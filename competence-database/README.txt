Encoding Verfahren (Stand 26.05.2014)
	- Identifier werden (alt) in CompOntologyAccess encodiert. Wenn die Formatierung des Strings zum Display wichtig ist, wird eine lesbare Form
	als Dataproperty "definition abgespeichert"
	- Die DAO-Schicht kodiert die Identifier in CompetenceOntologyDao (ähnlich zu dem alten Verfahren)
	- Für die SingletonDaos werden die Identifier nach dem alten Verfahren (oben) kodiert
	- Identifier dürfen nicht mit einem Sonderzeichen oder einer Zahl beginnen
	- Moodle Kurs Ids werden als "n"+Id kodiert. 

Festgelegte Strings (Stand 26.05.2014)
Prefixe, Dateinamen ... etc sind in der Klasse MagicStrings als default definiert