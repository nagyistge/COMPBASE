package uzuzjmd.rcd.competence

import uzuzjmd.owl.competence.ontology.CompObjectProperties

object RCDFilter {
  type CompetenceTriple = (String, String, String)
  type CompetenceFilter = CompetenceTriple => Boolean
  
  
  def isObjectPropertyTriple(triple: CompetenceTriple): Boolean = {
    CompObjectProperties.values.contains(triple._2)
  }

  def isSubClassTriple(triple: CompetenceTriple): Boolean = {
    CompObjectProperties.SubCompetenceOf.equals(CompObjectProperties.valueOf(triple._2))
  }
  
  def isSubOperatorTriple(triple: CompetenceTriple): Boolean = {
    CompObjectProperties.SubOperatorOf.equals(CompObjectProperties.valueOf(triple._2))
  }
  
 
  
}