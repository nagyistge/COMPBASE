package uzuzjmd.owl.reasoning.jena

import uzuzjmd.owl.competence.ontology.CompObjectProperties
import sun.reflect.MagicAccessorImpl
import uzuzjmd.owl.util.MagicStrings
import uzuzjmd.owl.competence.ontology.CompOntClass

object ReasoningMaps {
	/**
	 * returns (?a objectProperty ?b)
	 */
	def op2reasonableString (objectProperty : CompObjectProperties) : String = {
	   return "(?a "+ MagicStrings.PREFIX+objectProperty.name() + " ?b)" ;
	}

    /**
     * returns (?c rdfs:subClassOf className)
     */
	def op2reasonableString (className : CompOntClass) : String = {
	   return "(?c rdfs:subClassOf "+ MagicStrings.PREFIX+className.name()+")" ;
	}		
		
}