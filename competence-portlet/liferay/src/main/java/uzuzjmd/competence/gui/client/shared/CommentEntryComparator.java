package uzuzjmd.competence.gui.client.shared;

import java.util.Comparator;

import uzuzjmd.competence.gui.client.shared.dto.CommentEntry;

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
