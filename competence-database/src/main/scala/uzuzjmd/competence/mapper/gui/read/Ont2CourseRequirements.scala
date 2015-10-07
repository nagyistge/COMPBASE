package uzuzjmd.competence.mapper.gui.read

import uzuzjmd.competence.owl.access.AccessHelper
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.CourseContext

/**
 * @author dehne
 */
object Ont2CourseRequirements extends TDBREADTransactional[String, String] with AccessHelper {

  def convert(changes: String): String = {
    return execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: String): String = {
    val courseContextIndividual = new CourseContext(comp, changes).createIndividual;
    val requirementsLiteral = extractRequirementsLiteral(comp);
    val statement = courseContextIndividual.getProperty(requirementsLiteral);

    if (statement != null) {
      return statement.asTriple().getObject().getLiteralLexicalForm();
    } else {
      return ""
    }
  }

}