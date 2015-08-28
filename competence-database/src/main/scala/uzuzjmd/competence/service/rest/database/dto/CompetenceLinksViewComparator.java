package uzuzjmd.competence.service.rest.database.dto;

import java.util.Comparator;

import uzuzjmd.competence.shared.dto.CompetenceLinksView;

public class CompetenceLinksViewComparator implements Comparator<CompetenceLinksView> {

	@Override
	public int compare(CompetenceLinksView o1, CompetenceLinksView o2) {
		return o1.compareTo(o2);
	}

}
