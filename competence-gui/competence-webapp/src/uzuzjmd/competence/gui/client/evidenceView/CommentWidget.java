package uzuzjmd.competence.gui.client.evidenceView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends Composite {

	private static CommentWidgetUiBinder uiBinder = GWT
			.create(CommentWidgetUiBinder.class);

	interface CommentWidgetUiBinder extends UiBinder<Widget, CommentWidget> {
	}

	public CommentWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
