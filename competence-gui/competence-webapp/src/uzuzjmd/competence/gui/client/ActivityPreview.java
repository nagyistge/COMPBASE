package uzuzjmd.competence.gui.client;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class ActivityPreview extends FocusPanel implements MouseOverHandler,
		MouseOutHandler {
	private PopupPanel popupPanel;
	private String url;

	public ActivityPreview(String url) {
		super();
		this.url = url;
		popupPanel = new PopupPanel(true, true);
		popupPanel.setGlassEnabled(true);
		popupPanel.getElement().setClassName("preview-container");
		popupPanel.add(new Label("showing the content"));
		this.addMouseOverHandler(this);
		this.addMouseOutHandler(this);
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
		showPreview(url, ".region-content", ".preview-container");
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		removePreview(".preview-container");
		this.popupPanel.hide();
	}
}
