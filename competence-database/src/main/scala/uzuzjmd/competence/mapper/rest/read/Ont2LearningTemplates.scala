package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.shared.StringList

/**
 * @author dehne
 */
object Ont2LearningTemplates extends TDBReadTransactional[Any, StringList] {
  def convert(): StringList = {
    return executeNoParam(getLearningTemplates _)
  }

  private def getLearningTemplates(comp: CompOntologyManager): StringList = {
    val result = comp.getUtil().getAllInstanceDefinitions(CompOntClass.LearningProjectTemplate);
    val learningTemplates = new StringList(result);
    return learningTemplates
  }
}
