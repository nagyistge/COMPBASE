package uzuzjmd.competence.gui.client;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class ActivityPreview extends FocusPanel implements MouseOverHandler,
		MouseOutHandler {
	private PopupPanel popupPanel;
	private String url;

	public ActivityPreview(String url, String aktivitätslabel) {
		super();
		this.url = url;
		this.getElement().setClassName("preview-focus-panel");
		setupPreviewPopup(url);
		setupActivityPreviewContent(url, aktivitätslabel);
		this.addMouseOverHandler(this);
		this.addMouseOutHandler(this);
	}

	private void setupActivityPreviewContent(String url, String aktivitätslabel) {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		this.add(horizontalPanel);
		Label label = new Label(aktivitätslabel);
		Anchor anchor = new Anchor("show in moodle", url);
		horizontalPanel.add(label);
		horizontalPanel.add(anchor);
	}

	private void setupPreviewPopup(String url) {
		popupPanel = new PopupPanel(true);
		popupPanel.setGlassEnabled(true);
		popupPanel.center();
		ScrollPanel scroll = new ScrollPanel();
		popupPanel.add(scroll);
		scroll.getElement().setClassName("preview-container");
		showPreview(url, ".region-content", ".preview-container");
	}

	public native void showPreview(String url, String selector, String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
	}-*/;

	public native void removePreview(String whereTo)/*-{
		$wnd.removePreview(whereTo);
	}-*/;

	@Override
	public void onMouseOver(MouseOverEvent event) {
		popupPanel.show();
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		// removePreview(".preview-container");
		this.popupPanel.hide();
	}

}
