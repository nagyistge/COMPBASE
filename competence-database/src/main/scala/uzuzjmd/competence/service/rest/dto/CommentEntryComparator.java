package uzuzjmd.competence.service.rest.dto;

import java.util.Comparator;

public class CommentEntryComparator implements Comparator<CommentEntry> {

	@Override
	public int compare(CommentEntry arg0, CommentEntry arg1) {
		if (arg0.getCreated().equals(arg1)) {
			return 0;
		} else {
			return (arg0.getCreated() > arg1.getCreated()) ? 1 : -1;
		}

	}

}
