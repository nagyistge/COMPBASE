package uzuzjmd.competence.gui.client.competencegraph;

import uzuzjmd.competence.gui.shared.widgets.ClickMenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceClickPanel extends ClickMenu {

	private String idClicked;
	private String nodeClicked;

	public CompetenceClickPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	private static CompetenceClickPanelUiBinder uiBinder = GWT
			.create(CompetenceClickPanelUiBinder.class);

	interface CompetenceClickPanelUiBinder extends
			UiBinder<Widget, CompetenceClickPanel> {
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setId(String id) {
		this.idClicked = id;
	}

	@Override
	public void setNodeId(String nodeId) {
		this.nodeClicked = nodeId;
	}

	@Override
	public void loadContent() {
		// TODO Auto-generated method stub

	}

}
