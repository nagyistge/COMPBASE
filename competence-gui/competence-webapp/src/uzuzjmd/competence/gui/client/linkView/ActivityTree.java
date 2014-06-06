package uzuzjmd.competence.gui.client.linkView;

import static com.google.gwt.query.client.GQuery.$;

import java.util.LinkedList;
import java.util.List;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.Evidence;
import uzuzjmd.competence.gui.shared.widgets.MyTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.GQuery;
import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreeLoaderListener;

/**
 * 
 * @author Julian Dehne
 * 
 */
public class ActivityTree extends MyTreePanel {

	public ActivityTree(String databaseConnectionString, String rootLabel,
			String className, Integer width, Integer height, String title,
			ContextFactory contextFactory) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory);
		// TODO Auto-generated constructor stub
	}

	protected XMLTreeLoader initXMLLoader() {
		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(databaseConnectionString);
		loader.setMethod(Connection.GET);
		loader.setRootTag("userTrees");
		loader.setFolderTitleMapping("@name");
		loader.setFolderTag("activity");
		loader.setLeafTitleMapping("@name");
		loader.setLeafTag("activityEntry");
		// loader.setQtipMapping("@treetipp");
		loader.setDisabledMapping("@disabled");
		loader.setCheckedMapping("@checked");
		loader.setIconMapping("@icon");
		// loader.setHrefMapping("moodleUrl");
		loader.setHrefTargetMapping("moodleUrl");

		return initPreviewHack(loader);
	}

	public List<Evidence> getSelectedEvidences() {
		List<Evidence> evidences = new LinkedList<Evidence>();
		MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
				.getSelectionModel();
		TreeNode[] nodes = selectionModel.getSelectedNodes();
		for (TreeNode treeNode : nodes) {
			if (treeNode.isLeaf()) {
				evidences.add(new Evidence(treeNode.getText(), treeNode.getUI()
						.getAnchor().getAttribute("target"),
						((TreeNode) ((TreeNode) treeNode.getParentNode())
								.getParentNode()).getText()));
			}
		}
		return evidences;
	}

	public List<String> getAllowedNodeNames() {
		List<String> result = new LinkedList<String>();
		MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
				.getSelectionModel();
		TreeNode[] nodes = selectionModel.getSelectedNodes();
		for (TreeNode treeNode : nodes) {
			if (treeNode.getUI().getEl().hasTagName("activityEntry")) {
				result.add(treeNode.getText());
			}
		}
		return result;
	}

	private XMLTreeLoader initPreviewHack(final XMLTreeLoader loader) {
		loader.addListener(new TreeLoaderListener() {

			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onLoad(TreeLoader self, TreeNode node, String response) {

				GQuery gqueryNode = $(".x-tree-node-anchor");
				for (int i = 0; i < gqueryNode.length(); i++) {
					Element element = gqueryNode.get(i);
					if (element.getAttribute("target").trim() != ""
							&& element.hasAttribute("target")) {
						GWT.log("attaching preview to target"
								+ element.getAttribute("target"));
						if (element.hasChildNodes()) {
							String id = ((Element) element.getChild(0)).getId();
							((Element) element.getChild(0))
									.removeAttribute("qtip");
							if (id == null) {
								GWT.log("could not attach to child because of false structure");
							} else {
								GWT.log("attaching preview to id " + id);
								Competence_webapp.showPreview(
										element.getAttribute("target"),
										".region-content", "#" + id);
								// Competence_webapp.showPreview(
								// element.getAttribute("target"),
								// ".region-main", "#" + id);
							}
						}

					}
				}
				// gqueryNode.after("hello people");
			}

			@Override
			public boolean doBeforeLoad(TreeLoader self, TreeNode node) {
				return true;
			}
		});
		return loader;
	}

}
