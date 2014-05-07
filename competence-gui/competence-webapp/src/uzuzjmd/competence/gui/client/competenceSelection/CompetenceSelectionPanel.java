package uzuzjmd.competence.gui.client.competenceSelection;

import java.util.ArrayList;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.CheckableTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public class CompetenceSelectionPanel extends CheckableTreePanel {

	public CompetenceSelectionPanel(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height,
			String title, ContextFactory contextFactory) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory);
		// TODO Auto-generated constructor stub
	}

	public CompetenceSelectionPanel(String dataString,
			ContextFactory contextFactory) {
		// TODO Auto-generated constructor stub
		super(dataString, "Kompetenzen", "competenceView", 650, 250,
				"Kompetenzen", contextFactory);
	}

	public void setCompetenceSelected() {
		Resource resource = new Resource(contextFactory.getServerURL()
				+ "/competences/json/selected/" + contextFactory.getCourseId());
		resource.get().send(new JsonCallback() {

			@Override
			public void onSuccess(Method arg0, JSONValue arg1) {
				ArrayList<String> list = new ArrayList<String>();
				convertToList(arg1, list);
				doSelect(getTreePanel().getRootNode(), list);

			}

			private void convertToList(JSONValue arg1, ArrayList<String> list) {
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
		loader.setQtipMapping("@qtip");
		loader.setDisabledMapping("@disabled");
		loader.setCheckedMapping("isCompulsory");
		return loader;
	}

}
