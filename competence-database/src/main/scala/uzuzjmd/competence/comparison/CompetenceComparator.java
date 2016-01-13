package uzuzjmd.competence.comparison;


import uzuzjmd.competence.monopersistence.daos.Competence;

/**
 * this wraps different implementations of competence comparators that
 * are able to semantically link competences based
 * on computerlinguistic methods
 */
public interface CompetenceComparator {
	/**
	 * TODO create suggestions in framework
	 * @param dao1
	 * @param dao2
     * @return
     */
	Boolean isSimilar(Competence dao1, Competence dao2);
}
