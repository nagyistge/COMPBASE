package uzuzjmd.competence.gui.shared;

import static com.google.gwt.query.client.GQuery.$;
import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.ContextFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.GQuery;
import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreeLoaderListener;

/**
 * 
 * @author Julian Dehne
 * 
 */
public class ActivityPanel2 extends MyTreePanel {

	public ActivityPanel2(String databaseConnectionString, String rootLabel,
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
		loader.setQtipMapping("@qtip");
		loader.setDisabledMapping("@disabled");
		loader.setCheckedMapping("@checked");
		loader.setIconMapping("@icon");
		// loader.setHrefMapping("moodleUrl");
		loader.setHrefTargetMapping("moodleUrl");

		return initPreviewHack(loader);
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
