package uzuzjmd.competence.gui.shared.widgets;

import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.Composite;

public abstract class ClickMenu extends Composite implements MouseOutHandler {

	/**
	 * The id of the element clicked The text of the element clicked
	 * 
	 * @param id
	 * @param widget
	 * @param nodeId
	 */
	public ClickMenu() {
		// TODO Auto-generated constructor stub
	}

	public abstract void setId(String id);

	public abstract void setNodeId(String nodeId);

	public abstract void loadContent();
}
