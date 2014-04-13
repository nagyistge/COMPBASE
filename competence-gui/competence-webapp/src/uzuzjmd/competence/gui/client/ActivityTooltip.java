package uzuzjmd.competence.gui.client;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.ToolTip;

public class ActivityTooltip extends ToolTip {

	private String url;
	private String aktivitätslabel;

	public ActivityTooltip(String url, String aktivitätslabel) {
		this.url = url;
		this.aktivitätslabel = aktivitätslabel;
	}

	public void init(Panel panel1) {
		// this.setAutoHide(false);
		setSize(400, 400);
		setShadow(true);
		setAnimCollapse(true);
		this.applyTo(panel1);
		this.getElement().setClassName("preview-container");
		this.showPreview(url, ".region-content", "#"
				+ this.getElement().getId());

	}

	public native void showPreview(String url, String selector, String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
	}-*/;

	public native void removePreview(String whereTo)/*-{
		$wnd.removePreview(whereTo);
	}-*/;
}
