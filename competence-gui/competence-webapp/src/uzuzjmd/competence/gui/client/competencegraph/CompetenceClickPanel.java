package uzuzjmd.competence.gui.client.competencegraph;

import uzuzjmd.competence.gui.shared.widgets.ClickMenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.DecoratorPanel;

public class CompetenceClickPanel extends ClickMenu {

	private String idClicked;
	private String nodeClicked;
	

	public CompetenceClickPanel() {
		initWidget(uiBinder.createAndBindUi(this));		
		this.buttonWrapper.setVisible(true);
		this.buttonWrapper.setStyleName("competenceLinkButtonWrapper", true);
		this.setStyleName("competenceLinkButtonWrapper", true);
		
	}

	private static CompetenceClickPanelUiBinder uiBinder = GWT
			.create(CompetenceClickPanelUiBinder.class);
	@UiField Button deleteRequirementsButton;
	@UiField Button createRequirmentsbutton;
	@UiField DecoratorPanel buttonWrapper;

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

	@UiHandler("deleteRequirementsButton")
	void onDeleteRequirementsButtonClick(ClickEvent event) {
	}
	@UiHandler("createRequirmentsbutton")
	void onCreateRequirmentsbuttonClick(ClickEvent event) {
	}
}
