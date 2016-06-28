package uzuzjmd.competence.gui.client.shared;

import java.util.Comparator;

import uzuzjmd.competence.gui.client.shared.dto.CommentData;

public class CommentDataComparator implements Comparator<CommentData> {

	@Override
	public int compare(CommentData arg0, CommentData arg1) {
		if (arg0.getCreated().equals(arg1)) {
			return 0;
		} else {
			return (arg0.getCreated() > arg1.getCreated()) ? 1 : -1;
		}

	}

}
