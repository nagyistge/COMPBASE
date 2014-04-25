package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.service.rest.dto.CompetenceTree;

public class CompetenceServiceWrapper {
	public static CompetenceTree[] getCompetenceTree() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(
				compOntologyManager);
		return ont2CompetenceTree.getComptenceTree().toArray(
				new CompetenceTree[0]);
	}
}
