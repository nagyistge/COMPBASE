package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.shared.CatchwordSelectionTree;
import uzuzjmd.competence.gui.shared.CompetenceSelectionPanel;
import uzuzjmd.competence.gui.shared.MyTreePanel;
import uzuzjmd.competence.gui.shared.OperatorSelectionPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceSelectionWidget extends Composite {

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	interface CompetenceSelectionWidgetUiBinder extends
			UiBinder<Widget, CompetenceSelectionWidget> {
	}

	public CompetenceSelectionWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		MyTreePanel competencePanel = new CompetenceSelectionPanel(
				"http://localhost:8084/competences/tree/xml/crossdomain/4/nocache",
				"Kompetenzen", "competenceView", 600, "Kompetenzen");
		// panel.add(competencePanel);
		competenceTreeCaptionPanel.add(competencePanel);

		MyTreePanel operatorTree = new OperatorSelectionPanel(
				"http://localhost:8084/competences/operatortree/xml/crossdomain/4/nocache",
				"Operatoren", "operatorView", 160, "Operatoren");
		operatorCaptionPanel.add(operatorTree);

		MyTreePanel catchwordTree = new CatchwordSelectionTree(
				"http://localhost:8084/competences/catchwordtree/xml/crossdomain/4/nocache",
				"Schlagworte", "catchwordView", 200, "Schlagworte");
		catchwordCaptionPanel.add(catchwordTree);
	}

	@UiField
	VerticalPanel competenceTreeContainer;
	@UiField
	CaptionPanel competenceTreeCaptionPanel;
	@UiField
	HorizontalPanel competenceFilterPanel;
	@UiField
	VerticalPanel operatorPanel;
	@UiField
	CheckBox requiredFlagBox;
	@UiField
	CaptionPanel catchwordCaptionPanel;
	@UiField
	CaptionPanel operatorCaptionPanel;

}
