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
import uzuzjmd.rcd.generated.Metadata
import uzuzjmd.rcd.generated.Statement
import uzuzjmd.rcd.generated.Statementtext
import uzuzjmd.rcd.generated.Rdceoschema
import uzuzjmd.rcd.generated.Rdceoschemaversion
import uzuzjmd.owl.competence.ontology.CompOntClass
import uzuzjmd.owl.util.MagicStrings
import edu.uci.ics.jung.io.graphml.Metadata

object CSV2RCD {
	def mapCompetence (csvCompetences : Buffer[FilteredCSVCompetence]) : Buffer[Rdceo] = {
	   return csvCompetences.map(a => constructRDCEO(a));	   
	}
	
	def constructRDCEO(filteredCSVCompetence: FilteredCSVCompetence) : Rdceo = {
	  val rdceo = new Rdceo	  	  
	  
	  //create titel (entspricht dem Individual der Competence-Klasse)
	  val titel = new Title()
	  val langstringsTitle = titel.getLangstring().asScala	  	  
	  langstringsTitle+=(createLangString("Kompetenz" + filteredCSVCompetence.competence.hashCode()))	  
	  rdceo.setTitle(titel)
	  
	  //create description
	  val description = new Description
	  description.getLangstring().add(createLangString(filteredCSVCompetence.competence));	  
	  rdceo.setDescription(description)
	  
	  //create identifier
	  val identifier = new Identifier
	  identifier.setValue(MagicStrings.PREFIX)
	  rdceo.setIdentifier(identifier)
	  
	  //create definition
	  val definition = new Definition
		  //create model
	  	  val model = new Model
		  model.setValue("http://www.kmk.org/bildung-schule/allgemeine-bildung/lehrer/lehrerbildung.html");
		  definition.setModel(model);	  
		  //createStatments
		  val statements = definition.getStatement().asScala
		  statements.appendAll(filteredCSVCompetence.catchwordsFiltered.map(a=>createStatement(a, CompOntClass.Catchword.name())))
		  statements.append(createStatement(filteredCSVCompetence.evidencen,CompOntClass.Evidence.name()))
		  statements.appendAll(filteredCSVCompetence.metacatchwords.map(a=>createStatement(a, CompOntClass.MetaCatchword.name())))
		  statements.append(createStatement(filteredCSVCompetence.operator,CompOntClass.Operator.name()))		  
		  statements.append(createStatement(filteredCSVCompetence.learner, CompOntClass.Learner.name()))
      rdceo.getDefinition().asScala+=definition
	  // createMetaData
	  val rdceoSchema = new Rdceoschema
	  rdceoSchema.setValue("IMS RDCEO")
	  val rdceoSchemaVersion = new Rdceoschemaversion
	  rdceoSchemaVersion.setValue("1.0")
	  //val metadata = new Metadata
	  //rdceo.setMetadata(metadata)
	  //rdceo.getMetadata().setRdceoschema(rdceoSchema)
	  //rdceo.getMetadata().setRdceoschemaversion(rdceoSchemaVersion)
	  
	  return rdceo
	}
	
	def createLangString( toConvert : String) : Langstring = {
	  val langstring = new Langstring
	  langstring.setValue(toConvert)
	  return langstring;
	}
	
	/**
	 * Statementtext entspricht dem Individual
	 * Statementname entspricht der ObjectProperty
	 */
	def createStatement (toConvert : String, toConvertConcept : String) : Statement = {
	  val statement = new Statement
	  statement.setStatementname(toConvertConcept)
	  val statementtext = new Statementtext
	  statementtext.getLangstring().add(createLangString(toConvert))
	  statement.setStatementtext(statementtext)		  
	  return statement
	}
}