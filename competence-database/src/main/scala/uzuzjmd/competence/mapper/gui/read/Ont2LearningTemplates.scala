package uzuzjmd.competence.mapper.gui.read

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.shared.StringList

/**
 * @author dehne
 */
object Ont2LearningTemplates extends TDBREADTransactional[Any, StringList] {
  def convert(): StringList = {
    return executeNoParam(getLearningTemplates _)
  }

  private def getLearningTemplates(comp: CompOntologyManager): StringList = {
    val result = comp.getUtil().getAllInstanceDefinitions(CompOntClass.LearningProjectTemplate);
    val learningTemplates = new StringList(result);
    return learningTemplates
  }
}