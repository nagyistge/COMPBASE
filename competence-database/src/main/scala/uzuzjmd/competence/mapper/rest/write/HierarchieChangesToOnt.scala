package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.shared.dto.{HierarchieChange, HierarchieChangeSet}

import scala.collection.JavaConverters.asScalaBufferConverter

object HierarchieChangesToOnt extends TDBWriteTransactional[HierarchieChangeSet] {

  def convert(changes: HierarchieChangeSet) {
    execute(convertChanges _, changes)
  }

  private def convertChanges(comp: CompOntologyManager, changes: HierarchieChangeSet) {
    changes.getElements().asScala.foreach(convertChange(comp, _))
  }

  private def convertChange(comp: CompOntologyManager, change: HierarchieChange) {
    val selectedCompetence = new Competence(comp, change.getNodeSelected(), change.getNodeSelected())
    val oldSuperClass = new Competence(comp, change.getOldClass(), change.getOldClass())
    val newSuperClass = new Competence(comp, change.getNewClass(), change.getNewClass())

    selectedCompetence.addSuperCompetence(newSuperClass)
    selectedCompetence.removeSuperCompetence(oldSuperClass)
  }
}