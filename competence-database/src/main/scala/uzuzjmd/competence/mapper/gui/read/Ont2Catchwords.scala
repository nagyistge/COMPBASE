package uzuzjmd.competence.mapper.gui.read

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.Competence

/**
 * @author dehne
 */
object Ont2Catchwords extends TDBREADTransactional[String, String] {

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