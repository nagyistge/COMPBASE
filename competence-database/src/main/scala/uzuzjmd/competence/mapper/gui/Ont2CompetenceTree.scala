package uzuzjmd.competence.mapper.gui

import scala.collection.JavaConverters._
import uzuzjmd.competence.service.rest.dto.CompetenceTree
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.MagicStrings
import com.hp.hpl.jena.ontology.OntProperty

class Ont2CompetenceTree(ontologyManager: CompOntologyManager) {
  def getComptenceTree(): List[CompetenceTree] = {
    val competenceClass = ontologyManager.getUtil().getClass(CompOntClass.Competence);
    val competences = competenceClass.listSubClasses().toList().asScala
    val singleTons = competences.map(x => ontologyManager.getUtil().createSingleTonIndividual(x))

    val iProperty = ontologyManager.getM.getOntProperty(MagicStrings.PREFIX + "definition")
    val definitions = singleTons.map(x => x.getPropertyValue(iProperty))
    val result = definitions.filterNot(_ == null).map(x => new CompetenceTree(x.toString(), "Kompetenz", "", null));
    // todo implement
    return result.toList
  }
}