package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabbedView extends Composite {

	private static TabbedViewUiBinder uiBinder = GWT
			.create(TabbedViewUiBinder.class);
	@UiField
	TabLayoutPanel tabPanel;
	@UiField
	HTMLPanel requirementTabPlaceholder;
	@UiField
	HTMLPanel linkTabPlaceholder;
	@UiField
	HTMLPanel progressTabPlaceholder;

	interface TabbedViewUiBinder extends UiBinder<Widget, TabbedView> {
	}

	public TabbedView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TabbedView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

}
