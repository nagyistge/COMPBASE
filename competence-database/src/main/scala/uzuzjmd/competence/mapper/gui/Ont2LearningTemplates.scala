package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.shared.StringList
import uzuzjmd.competence.owl.access.TDBREADTransactional

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