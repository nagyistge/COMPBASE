package uzuzjmd.competence.mapper.rcd

import scala.collection.mutable.Buffer
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import scala.collection.JavaConverters._
import uzuzjmd.competence.rcd.RCDFilter
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.rcd.generated.Rdceo
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass

/**
 * Diese Klasse mappt Schlagwörter im RDCEO-Format auf die Ontologie
 */
object RCD2Catchword extends RCDImplicits {
  /**
   * Looks up Competence for each Title
   * Then it searches for all Operators of this Competence
   * Then it makes the operator inherit them
   */
  def createMetaCatchwordRels(util: CompOntologyAccess, rcdeos: Seq[Rdceo]) {
    rcdeos.foreach(recdeo2catchword(util))
  }

  /**
   * maps the (sub)operator parts of the rcdeo to a model represantation
   * Hilfsfunktion createSubOperatorRels
   */
  private def recdeo2catchword(util: CompOntologyAccess)(rcdeo: Rdceo) {
    // suboperatorTriples.foreach(handleSubOperatorTriple(util))
    val title = rcdeo.getTitle()
    val statements = rcdeo.getDefinition().asScala.head.getStatement().asScala
    val catchwords = statements.filter(statement => statement.getStatementname().equals(CompOntClass.Catchword.name())).map(statement => statement.getStatementtext())
    val metacatchwords = statements.filter(statement => statement.getStatementname().equals(CompOntClass.MetaCatchword.name())).map(statement => statement.getStatementtext())
    catchwords.foreach(catchwords => metacatchwords.foreach(metacatchwords => handleCatchwordTriple(util)((title, metacatchwords, catchwords))))
  }

  /**
   * suboperatortriples: x._1 competence, x._2 metacatchwords,  x._3 catchwords,
   * Hilfsfunktion  handleRcdeoSubOperators
   */
  private def handleCatchwordTriple(util: CompOntologyAccess)(input: RCDFilter.OperatorTriple) {
    val catchwordsIndividual: Individual = util.createSingleTonIndividualWithClass2(input._3)
    val competenceIndividual: Individual = util.createSingleTonIndividual(input._1)
    val metacatchwordsClass: OntClass = util.createSingleTonIndividualWithClass(input._2, input._2)
    metacatchwordsClass.addSuperClass(util.createOntClassForString(CompOntClass.Catchword.name()))
    catchwordsIndividual.getOntClass().addSuperClass(metacatchwordsClass)
    util.createObjectPropertyWithIndividual(catchwordsIndividual, competenceIndividual, CompObjectProperties.CatchwordOf)
  }

}