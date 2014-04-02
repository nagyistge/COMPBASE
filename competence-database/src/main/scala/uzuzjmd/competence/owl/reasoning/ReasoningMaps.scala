package uzuzjmd.competence.owl.reasoning

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.MagicStrings

object ReasoningMaps {
  /**
   * returns (?a objectProperty ?b)
   */
  def op2reasonableString(objectProperty: CompObjectProperties): String = {
    return "(?a " + MagicStrings.PREFIX + objectProperty.name() + " ?b)";
  }

  /**
   * returns (?c rdfs:subClassOf className)
   */
  def op2reasonableString(className: CompOntClass): String = {
    return "(?c rdfs:subClassOf " + MagicStrings.PREFIX + className.name() + ")";
  }

}