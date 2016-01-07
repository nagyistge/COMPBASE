package uzuzjmd.competence.persistence.owl.reasoning

import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.ontology.CompObjectProperties

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