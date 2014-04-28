package uzuzjmd.competence.service.rest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.service.rest.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.dto.OperatorXMLTree;

public class CompetenceServiceWrapper {

	private static Ont2CompetenceTree initOnt2Mapper(String[] selectedCatchwordArray, String[] selectedOperatorsArray) {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		List<String> catchwordarray = new LinkedList<String>();
		if (selectedCatchwordArray != null) {
			catchwordarray = Arrays.asList(selectedCatchwordArray);
		}

		List<String> operatorArray = new LinkedList<String>();
		if (selectedOperatorsArray != null) {
			operatorArray = Arrays.asList(selectedOperatorsArray);
		}
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(compOntologyManager, catchwordarray, operatorArray);
		return ont2CompetenceTree;
	}

	public static synchronized OperatorXMLTree[] getOperatorTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray);
		OperatorXMLTree[] tmpResult = ont2CompetenceTree.getOperatorXMLTree().toArray(new OperatorXMLTree[0]);
		return tmpResult;
	}

	public static synchronized CatchwordXMLTree[] getCatchwordTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray);
		CatchwordXMLTree[] tmpResult = ont2CompetenceTree.getCatchwordXMLTree().toArray(new CatchwordXMLTree[0]);
		return tmpResult;
	}

	public static CompetenceXMLTree[] getCompetenceTree(String[] selectedCatchwordArray, String[] selectedOperatorsArray) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTree();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}
}
