package uzuzjmd.competence.gui.client.competenceSelection;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.MyTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceSelectionWidget extends Composite {

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	interface CompetenceSelectionWidgetUiBinder extends
			UiBinder<Widget, CompetenceSelectionWidget> {
	}

	public CompetenceSelectionWidget(final ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));

		final MyTreePanel competencePanel = new CompetenceSelectionPanel(
				contextFactory.getServerURL()+"/competences/tree/xml/crossdomain/"+contextFactory.getCourseId()+"/nocache",
				"Kompetenzen", "competenceView", 650, 250, "Kompetenzen");
		// panel.add(competencePanel);
		competenceTreeCaptionPanel.add(competencePanel);

		final MyTreePanel operatorTree = new OperatorSelectionPanel(
				contextFactory.getServerURL()+"/competences/operatortree/xml/crossdomain/"+contextFactory.getCourseId()+"/nocache",
				"Operatoren", "operatorView", 300, 150,  "Operatoren");
		operatorCaptionPanel.add(operatorTree);

		final MyTreePanel catchwordTree = new CatchwordSelectionTree(
				contextFactory.getServerURL()+"/competences/catchwordtree/xml/crossdomain/"+contextFactory.getCourseId()+"/nocache",
				"Schlagworte", "catchwordView", 325, 200, "Schlagworte");
		catchwordCaptionPanel.add(catchwordTree);
		
		 // Hook up a handler to find out when it's clicked.
		requiredFlagBox.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	        boolean checked = ((CheckBox) event.getSource()).getValue();
	        competencePanel.reload(contextFactory.getServerURL()+"/competences/tree/xml/crossdomain/"+contextFactory.getCourseId()+"/nocache");
	      }
	    });
	}

	@UiField
	VerticalPanel competenceTreeContainer;
	@UiField
	Panel competenceTreeCaptionPanel;
	@UiField
	HorizontalPanel competenceFilterPanel;
	@UiField
	VerticalPanel operatorPanel;
	@UiField
	CheckBox requiredFlagBox;
	@UiField
	Panel catchwordCaptionPanel;
	@UiField
	Panel operatorCaptionPanel;

}
