package uzuzjmd.competence.main

import uzuzjmd.competence.persistence.abstractlayer.{ReadTransactional, CompOntologyManager}
import uzuzjmd.competence.persistence.owl.CompFileUtil

/**
  * writes the db to an protégé readable ontology format
  */
object OntologyWriter extends ReadTransactional[Any, Any] {

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
    println("RDF-FILE WRITTEN")
  }

}
