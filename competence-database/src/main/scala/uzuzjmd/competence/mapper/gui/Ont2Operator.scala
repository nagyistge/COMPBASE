package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.dao.Operator

/**
 * @author dehne
 */
object Ont2Operator extends TDBREADTransactional[String, String] {

  def convert(forCompetence: String): String = {
    return execute(getOperator, forCompetence)
  }

  def getOperator(comp: CompOntologyManager, forCompetence: String): String = {

    val competence = new Competence(comp, forCompetence, forCompetence, null);

    val operators = competence.getOperators()

    var result: String = "";
    if (!operators.isEmpty) {
      result = operators.head.getDefinition();
    }
    return result;
  }
}