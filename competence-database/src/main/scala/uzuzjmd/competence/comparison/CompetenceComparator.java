package uzuzjmd.competence.comparison;

import uzuzjmd.competence.owl.dao.Competence;

public interface CompetenceComparator {
	Boolean isSimilar(Competence dao1, Competence dao2);
}
