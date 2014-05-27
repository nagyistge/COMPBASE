package uzuzjmd.competence.service.rest.dto;

import java.util.Comparator;

public class CompetenceLinksViewComparator implements Comparator<CompetenceLinksView> {

	@Override
	public int compare(CompetenceLinksView o1, CompetenceLinksView o2) {
		return o1.compareTo(o2);
	}

}
