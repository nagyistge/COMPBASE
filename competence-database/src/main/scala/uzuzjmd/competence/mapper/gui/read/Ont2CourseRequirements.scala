package uzuzjmd.competence.mapper.gui.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.owl.AccessHelper

/**
 * @author dehne
 */
object Ont2CourseRequirements extends TDBReadTransactional[String, String] with AccessHelper {

  def convert(changes: String): String = {
    return execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: String): String = {
    val courseContextIndividual = new CourseContext(comp, changes).getIndividual;
    val requirementsLiteral = extractRequirementsLiteral(comp);
    if (courseContextIndividual == null) {
      return ""
    }
    val statement = courseContextIndividual.getProperty(requirementsLiteral);
    if (statement != null) {
      return statement.asTriple().getObject().getLiteralLexicalForm();
    } else {
      return ""
    }
  }

}