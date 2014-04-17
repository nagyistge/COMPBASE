package uzuzjmd.competence.gui.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
//import com.gwtext.client.core.Function;
import com.gwtext.client.core.Template;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Radio;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreeLoaderListener;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	private NodeList<Element> element;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");

		// test gwt ext

		Panel treePanelContainer = new Panel();
		treePanelContainer.setBorder(false);
		treePanelContainer.setPaddings(15);

		final Template template = new Template(
				"<div class=\"x-combo-list-item\">"
						+ "<img src=\"images/flags/{abbr}.gif\"> "
						+ "{activities }<div class=\"x-clear\"></div></div>");

		final TreePanel treePanel = new TreePanel();
		treePanel.setWidth(240);
		treePanel.setHeight(400);
		treePanel.setTitle("Aktivitätsübersicht");
		treePanel.setAnimate(true);
		treePanel.setEnableDD(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setSelectionModel(new MultiSelectionModel());
		treePanel.setShadow(false);

		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl("http://localhost:8083/moodle/activities/usertree/xml/crossdomain/2");
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

		final AsyncTreeNode root = new AsyncTreeNode("Aktivitäten", loader);
		treePanel.setRootNode(root);

		root.expand();
		treePanel.expandAll();

		treePanel.addTool(new Tool(Tool.REFRESH, new Function() {
			public void execute() {
				treePanel.getEl().mask("Loading", "x-mask-loading");
				root.reload();
				root.collapse(true, false);
				Timer timer = new Timer() {
					public void run() {
						treePanel.getEl().unmask();
						root.expand(true, true);
					}
				};
				timer.schedule(10000);
			}
		}, "Refresh"));

		FieldSet fieldSet = new FieldSet("Sort Direction");
		fieldSet.setFrame(false);

		final Radio ascRadio = new Radio();
		ascRadio.setName("direction");
		ascRadio.setBoxLabel("Ascending");
		ascRadio.setChecked(true);
		fieldSet.add(ascRadio);

		final Radio descRadio = new Radio();
		descRadio.setName("direction");
		descRadio.setBoxLabel("Descending");
		descRadio.setChecked(false);
		fieldSet.add(descRadio);

		FormPanel form = new FormPanel();
		form.setBorder(false);
		form.setFrame(false);
		form.setWidth(300);

		form.add(fieldSet);

		Button selectedButton = new Button("filtern",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
								.getSelectionModel();
						TreeNode[] nodes = selectionModel.getSelectedNodes();
						String nodesString = "";
						for (int i = 0; i < nodes.length; i++) {
							TreeNode node = nodes[i];
							nodesString += "<br>" + node.getText();
						}
						System.out.println("Selected Nodes :" + nodesString);
					}
				});

		Button sortButton = new Button("Sortieren",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
								.getSelectionModel();
						TreeNode[] nodes = selectionModel.getSelectedNodes();
						final boolean asc = ascRadio.getValue();
						// TODO implement
					}
				});

		form.addButton(selectedButton);
		form.addButton(sortButton);

		Panel verticalPanel = new Panel();
		verticalPanel.setLayout(new VerticalLayout(15));
		verticalPanel.add(treePanel);
		verticalPanel.add(form);
		treePanelContainer.add(verticalPanel);

		container.add(treePanelContainer);

	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//$wnd.previewdebug(url, selector, whereTo);

	}-*/;

	public static native void removePreview(String whereTo)/*-{
		$wnd.removePreview(whereTo);
	}-*/;
}
