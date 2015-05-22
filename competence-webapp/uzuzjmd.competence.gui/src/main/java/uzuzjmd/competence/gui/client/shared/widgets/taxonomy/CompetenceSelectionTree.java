package uzuzjmd.competence.gui.client.shared.widgets.taxonomy;

import java.util.ArrayList;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.shared.widgets.CheckableTreePanel;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.HierarchieChange;
import uzuzjmd.competence.service.rest.client.HierarchieChangeSet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Tree;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;

public class CompetenceSelectionTree extends CheckableTreePanel {

	private String selectedFilter;
	private HierarchieChangeSet changes;

	@Deprecated
	public CompetenceSelectionTree(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height,
			String title, LmsContextFactory contextFactory,
			Boolean courseContext) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory, null, courseContext);
		GWT.log("setting showchecked to: " + false);
		super.showChecked = false;
		if (courseContext) {
			treePanel.addListener(new MyTreePanelLister());
		}
	}

	public CompetenceSelectionTree(String dataString,
			LmsContextFactory contextFactory, String selectedFilter,
			Boolean showChecked, Boolean courseContext) {
		// TODO Auto-generated constructor stub
		super(dataString, "Kompetenzen", "competenceView", RootPanel.get(
				"rootContainer").getOffsetWidth() - 300, 350, "Kompetenzen",
				contextFactory, showChecked, courseContext);
		this.selectedFilter = selectedFilter;
		if (courseContext) {
			treePanel.addListener(new MyTreePanelLister());
		}
	}

	/**
	 * is used to edit the competence tree
	 * 
	 * @param dataString
	 * @param contextFactory
	 * @param selectedFilter
	 * @param showChecked
	 * @param courseContext
	 * @param editable
	 */
	public CompetenceSelectionTree(String dataString,
			LmsContextFactory contextFactory, String selectedFilter,
			Boolean showChecked, Boolean courseContext, boolean editable) {
		super(dataString, "Kompetenzen", "competenceView", RootPanel.get(
				"rootContainer").getOffsetWidth() - 300, 350, "Kompetenzen",
				contextFactory, showChecked, courseContext);
		this.selectedFilter = selectedFilter;
		this.changes = new HierarchieChangeSet();
		this.treePanel.addListener(new MyTreePanelChangeLister(changes));
	}

	public CompetenceSelectionTree(String dataString,
			LmsContextFactory contextFactory, String selectedFilter,
			Boolean showChecked, Boolean courseContext, boolean editable,
			Boolean clickaBoolean) {
		super(dataString, "Kompetenzen", "competenceView", RootPanel.get(
				"rootContainer").getOffsetWidth() - 300, 350, "Kompetenzen",
				contextFactory, showChecked, courseContext);
		this.selectedFilter = selectedFilter;
		this.changes = new HierarchieChangeSet();
		if (clickaBoolean) {
			this.treePanel.addListener(new MyTreePanelClickListener());
		} else {
			this.treePanel.addListener(new MyTreePanelChangeLister(changes));
		}
	}

	public void setSelectedFilter(String selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	private void setCompetenceSelected(final TreeNode node) {
		if (selectedFilter != null && selectedFilter.equals("selected")) {
			// String context = contextFactory.getOrganization();
			// if (super.courseContext) {
			// context = contextFactory.getCourseContext();
			// }
			Resource resource = new Resource(contextFactory.getServerURL()
					+ "/competences/json/" + selectedFilter + "/"
					+ contextFactory.getCourseContext());
			resource.get().send(new JsonCallback() {

				@Override
				public void onSuccess(Method arg0, JSONValue arg1) {
					ArrayList<String> list = new ArrayList<String>();
					convertToList(arg1, list);
					doSelect(node, list);
				}

				private void convertToList(JSONValue arg1,
						ArrayList<String> list) {
					JSONArray jsonArray = (JSONArray) arg1;
					if (jsonArray != null) {
						int len = jsonArray.size();
						for (int i = 0; i < len; i++) {
							list.add(jsonArray.get(i).toString());
						}
					}
				}

				@Override
				public void onFailure(Method arg0, Throwable arg1) {
					GWT.log("could not get selected competences from server");
				}
			});
		}
	}

	private void doSelect(TreeNode node, ArrayList<String> list) {
		MultiSelectionModel selectionModel = (MultiSelectionModel) getTreePanel()
				.getSelectionModel();
		TreeNode found = treePanel.getNodeById(node.getId());
		for (String selected : list) {
			selected = cleanString(selected);
			String nodeString = cleanString(found.getText());
			if (selected.equals(nodeString)) {
				selectionModel.select(node, true);
			}
		}

		for (int i = 0; i < node.getChildNodes().length; i++) {
			TreeNode child = (TreeNode) node.getChildNodes()[i];
			doSelect(child, list);
		}

	}

	private String cleanString(String selected) {
		return selected.trim().toLowerCase().replace("\"", "");
	}

	@Override
	protected XMLTreeLoader initXMLLoader() {
		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(databaseConnectionString);
		loader.setMethod(Connection.GET);
		loader.setRootTag("competenceRoot");
		loader.setFolderTitleMapping("@name");
		loader.setFolderTag("competence");
		loader.setLeafTitleMapping("@name");
		loader.setLeafTag("competence");
		// loader.setQtipMapping("@treetipp");
		loader.setDisabledMapping("@disabled");
		GWT.log(getShowChecked() + "");
		if (getShowChecked()) {
			loader.setCheckedMapping("isCompulsory");
		}
		return loader;
	}

	private class MyTreePanelLister extends TreePanelListenerAdapter {
		@Override
		public void onLoad(TreeNode node) {
			setCompetenceSelected(node);
		}
	}

	private class MyTreePanelClickListener extends TreePanelListenerAdapter {
		@Override
		public void onClick(TreeNode node, EventObject e) {
			// Window.alert("clicked" + node.getText());
			Controller.competenceEditTab.setCompetenceDescriptionTextArea(node
					.getText());
			Controller.competenceEditTab.setCompetenceToEdit(node.getText());

			Resource resource = new Resource(contextFactory.getServerURL()
					+ "/competences/json/operator");
			resource.addQueryParam("competence", node.getText()).get()
					.send(new TextCallback() {

						@Override
						public void onSuccess(Method method, String response) {
							Controller.competenceEditTab
									.setOperatorTextArea(response);
						}

						@Override
						public void onFailure(Method method, Throwable exception) {
							GWT.log("could not get operator");
						}
					});

			Resource resource2 = new Resource(contextFactory.getServerURL()
					+ "/competences/json/catchwords");
			resource2.addQueryParam("competence", node.getText()).get()
					.send(new TextCallback() {

						@Override
						public void onSuccess(Method method, String response) {
							Controller.competenceEditTab
									.setKeywordsTextArea(response);
						}

						@Override
						public void onFailure(Method method, Throwable exception) {
							GWT.log("could not get keywords");
						}
					});

		}
	}

	private class MyTreePanelChangeLister extends TreePanelListenerAdapter {

		private HierarchieChangeSet hierarchieChangeSet;

		public MyTreePanelChangeLister(
				final HierarchieChangeSet hierarchieChangeSet) {
			this.hierarchieChangeSet = hierarchieChangeSet;
		}

		@Override
		public void onMoveNode(Tree treePanel, TreeNode node,
				TreeNode oldParent, TreeNode newParent, int index) {
			hierarchieChangeSet.getElements().add(
					new HierarchieChange(oldParent.getText(), newParent
							.getText(), node.getText()));
		}
	}

	@Override
	protected Boolean getShowChecked() {
		return super.showChecked;
	}

	public HierarchieChangeSet getChanges() {
		return changes;
	}

	public void clearChanges() {
		if (changes != null) {
			changes.getElements().clear();
		}
	}

}
