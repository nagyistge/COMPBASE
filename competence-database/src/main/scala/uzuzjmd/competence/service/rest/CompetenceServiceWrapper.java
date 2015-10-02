package uzuzjmd.competence.service.rest;

import java.util.LinkedList;
import java.util.List;

import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree;
import uzuzjmd.competence.mapper.gui.Ont2CourseRequirements;
import uzuzjmd.competence.mapper.rest.CompetenceLinkedCourseToOnt;
import uzuzjmd.competence.mapper.rest.Ont2SelectedCompetencesForCourse;
import uzuzjmd.competence.service.rest.database.dto.CatchwordXMLTree;
import uzuzjmd.competence.service.rest.database.dto.CompetenceXMLTree;
import uzuzjmd.competence.service.rest.database.dto.OperatorXMLTree;
import uzuzjmd.competence.service.rest.model.dto.CompetenceLinkedToCourseData;

public class CompetenceServiceWrapper {

	private static Ont2CompetenceTree initOnt2Mapper(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course, Boolean compulsoryBoolean, String textFilter) {
		if (selectedCatchwordArray == null) {
			selectedCatchwordArray = new LinkedList<String>();
		}
		if (selectedOperatorsArray == null) {
			selectedOperatorsArray = new LinkedList<String>();
		}
		Ont2CompetenceTree ont2CompetenceTree = new Ont2CompetenceTree(selectedCatchwordArray, selectedOperatorsArray, course, compulsoryBoolean, textFilter);
		return ont2CompetenceTree;
	}

	public static synchronized OperatorXMLTree[] getOperatorTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, false, "");
		OperatorXMLTree[] tmpResult = ont2CompetenceTree.getOperatorXMLTree().toArray(new OperatorXMLTree[0]);
		return tmpResult;
	}

	public static synchronized CatchwordXMLTree[] getCatchwordTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, false, "");
		CatchwordXMLTree[] tmpResult = ont2CompetenceTree.getCatchwordXMLTree().toArray(new CatchwordXMLTree[0]);
		return tmpResult;
	}

	public static CompetenceXMLTree[] getCompetenceTree(List<String> selectedCatchwordArray, List<String> selectedOperatorsArray, String course, Boolean compulsoryBoolean, String textFilter) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwordArray, selectedOperatorsArray, course, compulsoryBoolean, textFilter);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTreeForCourse();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

	public static void linkCompetencesToCourse(String course, List<String> competences, Boolean compulsoryBoolean, String requirements) {
		CompetenceLinkedToCourseData changes = new CompetenceLinkedToCourseData(course, competences, compulsoryBoolean, requirements);
		CompetenceLinkedCourseToOnt.convert(changes);
	}

	public static String getRequirements(String course) {
		return Ont2CourseRequirements.convert(course);
	}

	public static String[] getSelected(String course) {
		return Ont2SelectedCompetencesForCourse.convert(course);
	}

	public static CompetenceXMLTree[] getCompetenceTreeForCourse(List<String> selectedCatchwords, List<String> selectedOperators, String course, Boolean compulsoryBoolean, String textFilter) {
		Ont2CompetenceTree ont2CompetenceTree = initOnt2Mapper(selectedCatchwords, selectedOperators, course, compulsoryBoolean, textFilter);
		List<CompetenceXMLTree> tmpResult = ont2CompetenceTree.getComptenceTreeForCourse();
		assert (!tmpResult.isEmpty());
		return tmpResult.toArray(new CompetenceXMLTree[0]);
	}

}
