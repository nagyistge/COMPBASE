package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.Competence
import scala.collection.JavaConverters._

object DeleteCompetenceInOnt extends TDBWriteTransactional[java.util.List[String]] {

  def convert(change: java.util.List[String]) {
    execute(convertDeleteCompetenceInOnt _, change);
  }

  def convertDeleteCompetenceInOnt(comp: CompOntologyManager, changes: java.util.List[String]) {
    System.out.println("deleting competences" + changes);
    changes.asScala.foreach { x => new Competence(comp, x, x, null).delete() }
  }
}