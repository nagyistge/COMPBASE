package uzuzjmd.rcd.mapper

import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.rcd.generated.Rdceo
import scala.collection.mutable.Buffer
import scala.collection.JavaConverters._
import uzuzjmd.rcd.generated.Title
import uzuzjmd.rcd.generated.Langstring
import uzuzjmd.rcd.generated.Description
import uzuzjmd.rcd.generated.Identifier
import uzuzjmd.rcd.generated.Definition
import uzuzjmd.rcd.generated.Model
import uzuzjmd.rcd.generated.Statement
import uzuzjmd.rcd.generated.Statementtext
import uzuzjmd.rcd.generated.Rdceoschema
import uzuzjmd.rcd.generated.Rdceoschemaversion

object CSV2RCD {
	def mapCompetence (csvCompetences : Buffer[FilteredCSVCompetence]) : Buffer[Rdceo] = {
	   return csvCompetences.map(a => constructRDCEO(a));	   
	}
	
	def constructRDCEO(filteredCSVCompetence: FilteredCSVCompetence) : Rdceo = {
	  val rdceo = new Rdceo
	  
	  //create titel
	  val titel = new Title()
	  val langstringsTitle = titel.getLangstring().asScala	  	  
	  langstringsTitle.append(createLangString("Kompetenz" + filteredCSVCompetence.competence.hashCode()))	  
	  rdceo.setTitle(titel)
	  
	  //create description
	  val description = new Description
	  description.getLangstring().add(createLangString(filteredCSVCompetence.competence));	  
	  rdceo.setDescription(description)
	  
	  //create identifier
	  val identifier = new Identifier
	  identifier.setValue("http://uzuzjmd.de/Kompetenzen")
	  rdceo.setIdentifier(identifier)
	  
	  //create definition
	  val definition = new Definition
		  //create model
	  	  val model = new Model
		  model.setValue("http://www.kmk.org/bildung-schule/allgemeine-bildung/lehrer/lehrerbildung.html");
		  definition.setModel(model);	  
		  //createStatments
		  val statements = definition.getStatement().asScala
		  statements.appendAll(filteredCSVCompetence.catchwordsFiltered.map(a=>createStatement(a, "catchword")))
		  statements.append(createStatement(filteredCSVCompetence.evidencen,"evidence"))
		  statements.appendAll(filteredCSVCompetence.metacatchwords.map(a=>createStatement(a, "metaoperator")))
		  statements.append(createStatement(filteredCSVCompetence.operator,"operator"))
		  statements.appendAll(filteredCSVCompetence.metacatchwords.map(a => createStatement(a, "metacatchwords")))
	  // createMetaData
	  val rdceoSchema = new Rdceoschema
	  rdceoSchema.setValue("IMS RDCEO")
	  val rdceoSchemaVersion = new Rdceoschemaversion
	  rdceoSchemaVersion.setValue("1.0")
	  rdceo.getMetadata().setRdceoschema(rdceoSchema)
	  rdceo.getMetadata().setRdceoschemaversion(rdceoSchemaVersion)
	  
	  return null;
	}
	
	def createLangString( toConvert : String) : Langstring = {
	  val langstring = new Langstring
	  langstring.setValue(toConvert)
	  return langstring;
	}
	
	def createStatement (toConvert : String, toConvertConcept : String) : Statement = {
	  val statement = new Statement
	  statement.setStatementname(toConvertConcept)
	  val statementtext = new Statementtext
	  statementtext.getLangstring().add(createLangString(toConvert))
	  statement.setStatementtext(statementtext)	  
	  return statement
	}
}