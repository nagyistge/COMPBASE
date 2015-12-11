package uzuzjmd.competence.persistence.owl

import com.hp.hpl.jena.rdf.model.Property
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager

/**
 * @author dehne
 */
trait AccessHelper {
  def extractRequirementsLiteral(compOntologyManager: CompOntologyManager): Property = {
    val requirementsLiteral = compOntologyManager.getM().createProperty(CompOntologyAccessScala.encode("requirements"));
    return requirementsLiteral;
  }
}