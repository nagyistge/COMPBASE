package uzuzjmd.competence.gui.client.competenceSelection;

import java.util.LinkedList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.XmlCallback;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.MyTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.gwtext.client.widgets.tree.TreeNode;

public class CompetenceSelectionWidget extends Composite {
	

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
	
	private CompetenceSelectionPanel competenceTree;
	private OperatorSelectionPanel operatorTree;
	private CatchwordSelectionTree catchwordTree;
	private ContextFactory contextFactory;
	

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	interface CompetenceSelectionWidgetUiBinder extends
			UiBinder<Widget, CompetenceSelectionWidget> {
	}

	public CompetenceSelectionWidget(final ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.contextFactory = contextFactory;

		this.competenceTree = new CompetenceSelectionPanel(
				contextFactory.getServerURL()
						+ "/competences/tree/xml/crossdomain/"
						+ contextFactory.getCourseId() + "/false/nocache",
				"Kompetenzen", "competenceView", 650, 250, "Kompetenzen");
		// panel.add(competencePanel);
		competenceTreeCaptionPanel.add(competenceTree);

		this.operatorTree = new OperatorSelectionPanel(
				contextFactory.getServerURL()
						+ "/competences/operatortree/xml/crossdomain/"
						+ contextFactory.getCourseId() + "/nocache",
				"Operatoren", "operatorView", 300, 150, "Operatoren");
		operatorCaptionPanel.add(operatorTree);

		this.catchwordTree = new CatchwordSelectionTree(
				contextFactory.getServerURL()
						+ "/competences/catchwordtree/xml/crossdomain/"
						+ contextFactory.getCourseId() + "/nocache",
				"Schlagworte", "catchwordView", 325, 200, "Schlagworte");
		catchwordCaptionPanel.add(catchwordTree);

		initCompulsoryFilter(contextFactory, competenceTree);
	}

	private void initCompulsoryFilter(final ContextFactory contextFactory,
			final MyTreePanel competencePanel) {
		// Hook up a handler to find out when it's clicked.
		requiredFlagBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean checked = ((CheckBox) event.getSource()).getValue();
				competencePanel.reload(contextFactory.getServerURL()
						+ "/competences/tree/xml/crossdomain/"
						+ contextFactory.getCourseId() + "/" + checked
						+ "/nocache");
			}
		});
	}

	
	
	public void handleSubmit(final String requirementText) {
		sendNonCompulsoryNodesToServer(requirementText);		
		
		
//		JSONValue request = ...

//		resource.post().json(request).send(new JsonCallback() {
//		    public void onSuccess(Method method, JSONValue response) {
//		        System.out.println(response);
//		    }
//		    public void onFailure(Method method, Throwable exception) {
//		        Window.alert("Error: "+exception);
//		    }
//		});
		
		// TODO
	}

	private void sendNonCompulsoryNodesToServer(final String requirementText) {
		Resource resource = new Resource( contextFactory.getServerURL() + "/competences/coursecontext/create/json/crossdomain/" + contextFactory.getCourseId()+ "/false");		
		resource.addQueryParam("requirements", requirementText);
		resource.addQueryParams("competences", competenceTree.convertSelectedTreeToList());		
		resource.post().send(new JsonCallback() {
			
			@Override
			public void onSuccess(Method method, JSONValue response) {
				GWT.log(response.toString());							
//				sendCompulsoryNodesToServer(requirementText);		
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log(exception.toString());				
			}
		});
	}

	private void sendCompulsoryNodesToServer(
			final String requirementText) {
		Resource resourceCompulsory = new Resource( contextFactory.getServerURL() + "/competences/coursecontext/create/json/crossdomain/" + contextFactory.getCourseId()+ "/true");		
		resourceCompulsory.addQueryParam("requirements", requirementText);
		resourceCompulsory.addQueryParams("competences", competenceTree.getCheckedNodes());		
		resourceCompulsory.post().send(new JsonCallback() {
			
			@Override
			public void onSuccess(Method method, JSONValue response) {
				GWT.log(response.toString());				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log(exception.toString());				
			}
		});
	}
	
	public void handleDeleteClick() {
		Resource resourceCompulsory = new Resource( contextFactory.getServerURL() + "/competences/coursecontext/delete/json/crossdomain/" + contextFactory.getCourseId());		
		resourceCompulsory.post().send(new JsonCallback() {			
			@Override
			public void onSuccess(Method method, JSONValue response) {
				GWT.log(response.toString());				
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log(exception.toString());				
			}
		});
		
	}

}
