package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos.Competence
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.shared.dto.{HierarchyChange, HierarchyChangeSet}

import scala.collection.JavaConverters.asScalaBufferConverter

object HierarchieChangesToOnt extends WriteTransactional[HierarchyChangeSet] {

  def convert(changes: HierarchyChangeSet) {
    execute(convertChanges _, changes)
  }

  private def convertChanges(changes: HierarchyChangeSet) {
    changes.getElements().asScala.foreach(convertChange(_))
  }

  private def convertChange( change: HierarchyChange) {
    val selectedCompetence = new Competence(change.getNodeSelected())
    val oldSuperClass = new Competence(change.getOldClass())
    val newSuperClass = new Competence(change.getNewClass())
    selectedCompetence.addSuperCompetence(newSuperClass)
    selectedCompetence.removeSuperCompetence(oldSuperClass)
  }
}