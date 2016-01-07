package uzuzjmd.competence.mapper.rcd

import uzuzjmd.competence.datasource.csv.FilteredCSVCompetence
import uzuzjmd.competence.persistence.ontology.{CompObjectProperties, CompOntClass}
import uzuzjmd.competence.datasource.rcd.generated.{Definition, Description, Langstring, Model, Rdceo, Rdceoschema, Rdceoschemaversion, Statement, Statementtext, Title}

import scala.collection.JavaConverters._

/**
 * Diese Klasse mappt das Excel-Zwischenformat auf das RDCEO-Format
 *
 */
object CSV2RCD {
  def mapCompetence(csvCompetences: Seq[FilteredCSVCompetence]): Seq[Rdceo] = {
    return csvCompetences.map(a => constructRDCEO(a));
  }

  def constructRDCEO(filteredCSVCompetence: FilteredCSVCompetence): Rdceo = {
    val rdceo = new Rdceo

    //create titel (entspricht dem Individual der Competence-Klasse)
    val titel = new Title()
    val langstringsTitle = titel.getLangstring().asScala
    langstringsTitle += (createLangString(filteredCSVCompetence.competence))
    rdceo.setTitle(titel)

    //create description
    val description = new Description
    description.getLangstring().add(createLangString(filteredCSVCompetence.competence));
    rdceo.setDescription(description)

    //create identifier
    //    val identifier = new Identifier
    //    identifier.setValue(MagicStrings.PREFIX)
    //    rdceo.setIdentifier(identifier)

    //create definition
    val definition = new Definition
    //create model
    val model = new Model
    model.setValue("http://www.kmk.org/bildung-schule/allgemeine-bildung/lehrer/lehrerbildung.html");
    //createStatments
    val statements = definition.getStatement().asScala
    statements.appendAll(filteredCSVCompetence.catchwordsFiltered.map(a => createStatement(a, CompOntClass.Catchword.name())))
    statements.append(createStatement(filteredCSVCompetence.evidencen, CompOntClass.Evidence.name()))
    statements.appendAll(filteredCSVCompetence.metacatchwords.map(a => createStatement(a, CompOntClass.MetaCatchword.name())))
    statements.append(createStatement(filteredCSVCompetence.metaoperator, CompOntClass.MetaOperator.name()))
    statements.append(createStatement(filteredCSVCompetence.operator, CompOntClass.Operator.name()))
    statements.append(createStatement(filteredCSVCompetence.learner, CompOntClass.Learner.name()))
    statements.append(createStatement(filteredCSVCompetence.supercompetence, CompObjectProperties.SubCompetenceOf.name()))
    rdceo.getDefinition().asScala += definition
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

  def createLangString(toConvert: String): Langstring = {
    val langstring = new Langstring
    langstring.setValue(toConvert.replaceAll("\r\n", ""))
    return langstring;
  }

  /**
   * Statementtext entspricht dem Individual
   * Statementname entspricht der ObjectProperty
   */
  def createStatement(toConvert: String, toConvertConcept: String): Statement = {
    val statement = new Statement
    statement.setStatementname(toConvertConcept)
    val statementtext = new Statementtext
    statementtext.getLangstring().add(createLangString(toConvert))
    statement.setStatementtext(statementtext)
    return statement
  }
}