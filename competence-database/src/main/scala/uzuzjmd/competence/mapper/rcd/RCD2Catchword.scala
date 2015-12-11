package uzuzjmd.competence.mapper.rcd

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccess

import scala.collection.mutable.Buffer
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import scala.collection.JavaConverters._
import uzuzjmd.competence.datasource.rcd.RCDFilter
import uzuzjmd.competence.datasource.rcd.generated.Rdceo
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.datasource.rcd.RCDMaps

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
    rcdeos.foreach(recdeo2catchword(util)(CompOntClass.Catchword)(CompOntClass.MetaCatchword))
    rcdeos.foreach(recdeo2catchword(util)(CompOntClass.Operator)(CompOntClass.MetaOperator))
  }

  private def recdeo2catchword(util: CompOntologyAccess)(compOntClass: CompOntClass)(metaClass: CompOntClass)(rcdeo: Rdceo) {
    // suboperatorTriples.foreach(handleSubOperatorTriple(util))
    val title = rcdeo.getTitle()
    val statements = rcdeo.getDefinition().asScala.head.getStatement().asScala.view.filterNot(statement => statement.getStatementtext() == null).filterNot(statement => statement.getStatementtext().trim().equals(""))
    val catchwords = statements.filter(statement => statement.getStatementname().equals(compOntClass.name())).map(statement => statement.getStatementtext()).view
    val metacatchwords = statements.filter(statement => statement.getStatementname().equals(metaClass.name())).map(statement => statement.getStatementtext()).view
    catchwords.foreach(catchwords => metacatchwords.foreach(metacatchwords => handleCatchwordTriple(util)(title, metacatchwords, catchwords)(RCDMaps.classToObjectProperty(compOntClass))))
  }

  /**
   * suboperatortriples: x._1 competence, x._2 metacatchwords,  x._3 catchwords,
   * Hilfsfunktion  handleRcdeoCatchwords and Metacatchwords
   */
  private def handleCatchwordTriple(util: CompOntologyAccess)(input: RCDFilter.OperatorTriple)(compObjectProperties: CompObjectProperties) {
    //println("title: " + input._1 + "		metacatchword:" + input._2 + "		catchword :" + input._3 )
    val catchwordRootClass = util.createOntClassForString(RCDMaps.objectPropertyToClass(compObjectProperties).name(), false)

    val catchwordResource = util.accessSingletonResource(input._3, false)
    //val catchwordsIndividual: Individual = util.createSingleTonIndividualWithClass2(input._3)
    //val catchwordClass: OntClass = util.createSingleTonIndividualWithClass(input._3)
    val catchwordsIndividual = catchwordResource.getIndividual()
    val catchwordClass = catchwordResource.getOntclass()

    val competenceResource = util.accessSingletonResource(input._1, false)
    val competenceIndividual: Individual = competenceResource.getIndividual()

    if (!(input._2 == null) && !input._2.equals("")) {
      val metacatchwordsClass: OntClass = util.createSingleTonIndividualWithClass(input._2, false, input._2)
      metacatchwordsClass.addSuperClass(catchwordRootClass)
      catchwordClass.addSuperClass(metacatchwordsClass)
    }
    catchwordClass.addSuperClass(catchwordRootClass)
    util.createObjectPropertyWithIndividual(catchwordsIndividual, competenceIndividual, compObjectProperties)
  }

}