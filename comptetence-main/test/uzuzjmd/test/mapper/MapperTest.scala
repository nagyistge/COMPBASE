package uzuzjmd.test.mapper

import uzuzjmd.rcd.mapper.CSV2RCD
import uzuzjmd.owl.util.CompOntologyManager
import uzuzjmd.csv.competence.FilteredCSVCompetence
import uzuzjmd.rcd.mapper.RCD2OWL
import uzuzjmd.rcd.generated.Rdceo
import uzuzjmd.owl.competence.ontology.CompOntClass
import scala.collection.JavaConverters._

object MapperTest {

  def main(args: Array[String]) {

    val rcdeo = new Rdceo
    val competence: String = "Lehramtstudent programmiert bemerkenswerte Programme"
    val catchwordsFiltered: List[String] = "Programmierung" :: Nil
    val operator: String = "programmieren"
    val metaoperator: String = "herstellen"
    val evidencen: String = "testevidenz"
    val metacatchwords: List[String] = "Programmierung" :: "Java" :: "Scala" :: Nil
    val learner: String = "Lehramtsstudent"
    val filteredCSVcomptence = new FilteredCSVCompetence(competence, catchwordsFiltered, operator, metaoperator, evidencen, metacatchwords, learner)
    val csvList = filteredCSVcomptence :: Nil
    val rcdeos = CSV2RCD.mapCompetence(csvList.toBuffer)

    val testrcdeo = rcdeos.head
    val statement = CSV2RCD.createStatement("tippen", CompOntClass.SubOperator.name())
    testrcdeo.getDefinition().asScala.head.getStatement().add(statement)
    val manager = new CompOntologyManager
    val ontModel = RCD2OWL.convert(rcdeos, manager)
    ontModel.listClasses().toSet().asScala.map(x => x.getLocalName())
    ontModel.listClasses().toSet().asScala.filter(x => x.getLocalName().equals("Thing") || x.getLocalName().equals("Resource")).map(x => (x.getLocalName(), "super:" + x.getSuperClass().getLocalName(), "sub:" + x.getSubClass().getLocalName))

  }

}