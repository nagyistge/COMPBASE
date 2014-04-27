package uzuzjmd.competence.service.rest;

import java.util.List;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree;

public class CompetenceServiceWrapper {
	public static synchronized CompetenceXMLTree[] getCompetenceTree() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(
				compOntologyManager);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree
				.getComptenceTree();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

	public static synchronized OperatorXMLTree[] getOperatorTree() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(
				compOntologyManager);

		OperatorXMLTree[] tmpResult = ont2CompetenceTree.getOperatorXMLTree()
				.toArray(new OperatorXMLTree[0]);
		return tmpResult;
	}
}
