package uzuzjmd.competence.comparison.simple;

import uzuzjmd.competence.comparison.CompetenceComparator;
import uzuzjmd.competence.comparison.simple.mapper.SimpleCompetenceComparatorMapper;
import uzuzjmd.competence.owl.dao.Competence;

public class SimpleCompetenceComparator implements CompetenceComparator {

	@Override
	public Boolean isSimilar(Competence dao1, Competence dao2) {

		String firstCompetence = dao1.getDefinition();
		String secondCompetence = dao2.getDefinition();

		// TODO Auto-generated method stub
		return isSimilarStrings(firstCompetence, secondCompetence);
	}

	private Boolean isSimilarStrings(String input1, String input2) {
		SimpleCompetenceComparatorMapper mapper = new SimpleCompetenceComparatorMapper();
		return mapper.isSimilarStrings(input1, input2);

	}
}
