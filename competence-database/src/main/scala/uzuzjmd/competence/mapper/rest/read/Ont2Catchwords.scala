package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, ReadTransactional}
import uzuzjmd.competence.persistence.dao.Competence

/**
 * @author dehne
 */
object Ont2Catchwords extends ReadTransactional[String, String] {

  def convert(forCompetence: String): String = {
    return execute(getCatchwords, forCompetence)
  }

  def getCatchwords(comp: CompOntologyManager, forCompetence: String): String = {
    val competence = new Competence(comp, forCompetence, forCompetence, null);
    val catchwords = competence.getCatchwords();

    var result: String = "";
    if (!catchwords.isEmpty) {
      for (catchword <- catchwords) {
        result += catchword.getDefinition() + ",";
      }
      result = result.substring(0, result.length() - 1);
    }

    return result;
  }
}