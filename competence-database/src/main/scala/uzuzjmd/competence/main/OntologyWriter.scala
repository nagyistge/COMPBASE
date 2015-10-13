package uzuzjmd.competence.main

import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional

object OntologyWriter extends TDBREADTransactional[Any, Any] {

  def main(args: Array[String]) {
    execute2(writeOut)
  }

  private def writeOut(comp: CompOntologyManager) {
    comp.getM().validate()
    val fileUtil = new CompFileUtil(comp.getM())
    fileUtil.writeOut();
  }

  def convert() {
    execute2(writeOut)
  }

}
