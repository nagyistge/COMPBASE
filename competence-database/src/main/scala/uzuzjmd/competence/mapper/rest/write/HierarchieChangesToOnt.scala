package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.shared.dto.{HierarchyChange, HierarchyChangeSet}

import scala.collection.JavaConverters.asScalaBufferConverter

object HierarchieChangesToOnt extends WriteTransactional[HierarchyChangeSet] {

  def convert(changes: HierarchyChangeSet) {
    execute(convertChanges _, changes)
  }

  private def convertChanges(comp: CompOntologyManager, changes: HierarchyChangeSet) {
    changes.getElements().asScala.foreach(convertChange(comp, _))
  }

  private def convertChange(comp: CompOntologyManager, change: HierarchyChange) {
    val selectedCompetence = new Competence(comp, change.getNodeSelected(), change.getNodeSelected())
    val oldSuperClass = new Competence(comp, change.getOldClass(), change.getOldClass())
    val newSuperClass = new Competence(comp, change.getNewClass(), change.getNewClass())

    selectedCompetence.addSuperCompetence(newSuperClass)
    selectedCompetence.removeSuperCompetence(oldSuperClass)
  }
}