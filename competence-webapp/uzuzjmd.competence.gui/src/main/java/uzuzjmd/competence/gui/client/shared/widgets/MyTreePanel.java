package uzuzjmd.competence.gui.client.shared.widgets;

import java.util.LinkedList;
import java.util.List;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public abstract class MyTreePanel extends Panel {
	protected final TreePanel treePanel;

	protected String databaseConnectionString;

	protected LmsContextFactory contextFactory;

	protected Boolean showChecked;

	public String title;

	private Integer width;

	private Integer height;

	private XMLTreeLoader xmlLoader;

	protected AsyncTreeNode rootNode;

	protected Boolean courseContext;

	public TreePanel getTreePanel() {
		return treePanel;
	}

	/**
	 * 
	 * 
	 * @param databaseConnectionString
	 * @param rootLabel
	 * @param className
	 * @param showChecked2
	 * @param courseContext
	 */
	public MyTreePanel(String databaseConnectionString, String rootLabel,
			String className, Integer width, Integer height, String title,
			LmsContextFactory contextFactory, Boolean showChecked2,
			Boolean courseContext) {
		super();

		this.contextFactory = contextFactory;
		this.courseContext = courseContext;
		this.title = title;
		this.width = width;
		this.height = height;
		this.showChecked = showChecked2;
		// to test
		GWT.log("width is: " + this.width);

		this.databaseConnectionString = databaseConnectionString;

		treePanel = initTreePanel();
		final XMLTreeLoader loader = initXMLLoader();
		xmlLoader = loader;

		final AsyncTreeNode root = new AsyncTreeNode(rootLabel, loader);
		rootNode = root;
		treePanel.setRootNode(rootNode);

		treePanel.expandAll();

		initReloadTool(treePanel, rootNode);

		this.add(treePanel);
		this.getElement().setClassName(className);
		treePanel.getEl().mask("Loading", "x-mask-loading");
		Timer timer = new Timer() {
			public void run() {
				treePanel.getEl().unmask();
			}
		};
		timer.schedule(500);

	}

	public void collapseAll() {
		rootNode.collapse(true, false);
	}

	public void reload(String dataConnection) {
		this.xmlLoader.setDataUrl(dataConnection);
		reloadTree(treePanel, rootNode);
	}

	public void reload() {
		reloadTree(treePanel, rootNode);
	}

	protected abstract XMLTreeLoader initXMLLoader();

	protected abstract Boolean getShowChecked();

	protected void initReloadTool(final TreePanel treePanel,
			final AsyncTreeNode root) {
		Tool tool = new Tool(Tool.REFRESH, new Function() {
			public void execute() {
				reloadTree(treePanel, root);
			}
		}, "Refresh");
		treePanel.addTool(tool);
	}

	private void reloadTree(final TreePanel treePanel, final AsyncTreeNode root) {
		treePanel.getEl().mask("Loading", "x-mask-loading");
		root.reload();
		root.collapse(true, false);
		Timer timer = new Timer() {
			public void run() {
				treePanel.getEl().unmask();
				root.expand(true, true);
			}
		};
		timer.schedule(1000);
	}

	public void reloadTree() {
		reload();
	}

	protected TreePanel initTreePanel() {
		final TreePanel treePanel = new TreePanel();
		treePanel.setTitle(title);
		treePanel.setAnimate(true);
		treePanel.setEnableDD(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setSelectionModel(new MultiSelectionModel());
		treePanel.setShadow(false);
		treePanel.setWidth(width);
		treePanel.setHeight(height);
		// treePanel.setAutoHeight(true);
		treePanel.setAutoScroll(true);
		treePanel.setDdScroll(true);
		treePanel.setPaddings(8);
		treePanel.setDraggable(false);
		treePanel.setRootVisible(true);
		treePanel.setCollapsible(true);

		return treePanel;
	}

	private TreeNode[] getSelectedNodes() {
		MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
				.getSelectionModel();
		TreeNode[] nodes = selectionModel.getSelectedNodes();
		for (TreeNode treeNode : nodes) {
			GWT.log("selected nodes" + treeNode.getText());
		}
		return nodes;
	}

	public void clearSelections() {
		MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
				.getSelectionModel();
		selectionModel.clearSelections();
	}

	public List<String> convertSelectedTreeToList() {
		List<String> selectedTreeNodes = new LinkedList<String>();
		for (TreeNode node : getSelectedNodes()) {
			selectedTreeNodes.add(node.getText());
		}
		return selectedTreeNodes;
	}

}