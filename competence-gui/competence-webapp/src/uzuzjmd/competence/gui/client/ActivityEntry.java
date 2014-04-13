package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ActivityEntry extends Composite {

	private static ActivityEntryUiBinder uiBinder = GWT
			.create(ActivityEntryUiBinder.class);
	@UiField
	HorizontalPanel activityEntryPanel;
	@UiField
	Image activityIcon;
	@UiField
	Anchor activityAnchor;
	@UiField
	FocusPanel activityFocusPanel;

	interface ActivityEntryUiBinder extends UiBinder<Widget, ActivityEntry> {
	}

	public ActivityEntry(String url, String iconClass, String activityName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.activityAnchor.setHref(url);
		this.activityAnchor.setText(activityName);
		this.activityIcon.getElement().setClassName(iconClass);
		this.getElement().setClassName("activiyOffFocus");
	}

	@UiHandler("activityFocusPanel")
	void onActivityFocusPanelMouseOver(MouseOverEvent event) {
		// todo implement
		this.getElement().setClassName("activiyInFocus");
	}

	@UiHandler("activityFocusPanel")
	void onActivityFocusPanelMouseOut(MouseOutEvent event) {
		this.getElement().setClassName("activiyOffFocus");
	}
}
