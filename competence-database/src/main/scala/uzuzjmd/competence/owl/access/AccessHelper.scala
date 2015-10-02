package uzuzjmd.competence.owl.access

import com.hp.hpl.jena.rdf.model.Property

/**
 * @author dehne
 */
trait AccessHelper {
  def extractRequirementsLiteral(compOntologyManager: CompOntologyManager): Property = {
    val requirementsLiteral = compOntologyManager.getM().createProperty(CompOntologyAccess.encode("requirements"));
    return requirementsLiteral;
  }
}