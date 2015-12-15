package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, ReadTransactional}
import uzuzjmd.competence.persistence.dao.Competence

/**
 * @author dehne
 */
object Ont2Operator extends ReadTransactional[String, String] {

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
