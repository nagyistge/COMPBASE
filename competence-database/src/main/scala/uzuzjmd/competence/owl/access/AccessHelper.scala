package uzuzjmd.competence.owl.access

import com.hp.hpl.jena.rdf.model.Property
import uzuzjmd.competence.owl.abstractlayer.CompOntologyAccess

/**
 * @author dehne
 */
trait AccessHelper {
  def extractRequirementsLiteral(compOntologyManager: CompOntologyManager): Property = {
    val requirementsLiteral = compOntologyManager.getM().createProperty(CompOntologyAccessScala.encode("requirements"));
    return requirementsLiteral;
  }
}