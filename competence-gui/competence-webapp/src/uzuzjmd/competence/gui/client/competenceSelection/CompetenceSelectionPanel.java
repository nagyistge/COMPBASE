package uzuzjmd.competence.gui.client.competenceSelection;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.CheckableTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreeLoaderListener;

public class CompetenceSelectionPanel extends CheckableTreePanel {

	private List<String> toSelect;

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

	// @Override
	// protected TreePanel initTreePanel() {
	// // TODO Auto-generated method stub
	// TreePanel treePanel = super.initTreePanel();
	// treePanel.addListener(new MyTreeListenerAdaptor(contextFactory,
	// treePanel));
	// return treePanel;
	// }

	// private class MyTreeListenerAdaptor extends TreePanelListenerAdapter {
	//
	// private ContextFactory contextFactory;
	// private TreePanel tree;
	//
	// public MyTreeListenerAdaptor(final ContextFactory contextFactory,
	// final TreePanel tree) {
	// this.contextFactory = contextFactory;
	// this.tree = tree;
	// }
	//
	// }

	private void setCompetenceSelected(final TreeNode node) {
		Resource resource = new Resource(contextFactory.getServerURL()
				+ "/competences/json/selected/" + contextFactory.getCourseId());
		resource.get().send(new JsonCallback() {

			@Override
			public void onSuccess(Method arg0, JSONValue arg1) {
				ArrayList<String> list = new ArrayList<String>();
				JSONArray jsonArray = (JSONArray) arg1;
				if (jsonArray != null) {
					int len = jsonArray.size();
					for (int i = 0; i < len; i++) {
						list.add(jsonArray.get(i).toString());
					}
				}
				toSelect = list;
				if (toSelect.contains(node.getText())) {
					node.select();
				}

			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get selected competences from server");
			}
		});
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
		loader.addListener(new TreeLoaderListener() {

			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
			}

			@Override
			public void onLoad(TreeLoader self, TreeNode node, String response) {
				if (toSelect == null) {
					setCompetenceSelected(node);
				} else if (toSelect.contains(node.getText())) {
					node.select();
				}
			}

			@Override
			public boolean doBeforeLoad(TreeLoader self, TreeNode node) {
				return true;
			}
		});
		return loader;
	}

}
