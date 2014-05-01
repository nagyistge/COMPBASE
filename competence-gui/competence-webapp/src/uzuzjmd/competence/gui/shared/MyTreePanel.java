package uzuzjmd.competence.gui.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
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
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public abstract class MyTreePanel extends Panel {
	protected final TreePanel treePanel;

	protected String databaseConnectionString;

	public String title;

	private Integer width;

	private Integer height;

	/**
	 * 
	 * 
	 * @param databaseConnectionString
	 * @param rootLabel
	 * @param className
	 */
	public MyTreePanel(String databaseConnectionString, String rootLabel,
			String className, Integer width, Integer height, String title) {
		super();
		this.title = title;
		this.width = width;
		this.height = height;
		this.databaseConnectionString = databaseConnectionString;
		// test gwt ext

//		Panel treePanelContainer = new Panel();
		// treePanelContainer.setBorder(false);
		// treePanelContainer.setPaddings(15);

		treePanel = initTreePanel();
		final XMLTreeLoader loader = initXMLLoader();
		final AsyncTreeNode root = new AsyncTreeNode(rootLabel, loader);
		treePanel.setRootNode(root);
		root.expand();
		treePanel.expandAll();
		initReloadTool(treePanel, root);
//		FormPanel buttonPanel = initButtons(treePanelContainer, treePanel,
//				width);
////
//		Panel verticalPanel = new Panel();
//		verticalPanel.setLayout(new VerticalLayout(15));
//		verticalPanel.add(treePanel);
//		// verticalPanel.add(buttonPanel);
//		treePanelContainer.add(verticalPanel);

//		treePanelContainer.add(treePanel);
//		this.add(treePanelContainer);
		this.add(treePanel);
		this.getElement().setClassName(className);
	}

	protected abstract XMLTreeLoader initXMLLoader();

	public FormPanel initButtons(Panel treePanelContainer,
			final TreePanel treePanel, Integer width) {
		FieldSet fieldSet = new FieldSet("Sortierreihenfolge");
		fieldSet.setFrame(false);

		final Radio ascRadio = new Radio();
		ascRadio.setName("direction");
		ascRadio.setBoxLabel("Aufsteigend");
		ascRadio.setChecked(true);
		fieldSet.add(ascRadio);

		final Radio descRadio = new Radio();
		descRadio.setName("direction");
		descRadio.setBoxLabel("absteigend");
		descRadio.setChecked(false);
		fieldSet.add(descRadio);

		FormPanel form = new FormPanel();
		form.setBorder(false);
		form.setFrame(false);
		form.setWidth(width);

		form.add(fieldSet);

		Button selectedButton = initSelectButton(treePanel);

		Button sortButton = initSortButton(ascRadio);

		form.addButton(selectedButton);
		form.addButton(sortButton);

		return form;
	}

	private Button initSortButton(final Radio ascRadio) {
		Button sortButton = new Button("Sortieren",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						TreeNode[] nodes = getSelectedNodes();
						final boolean asc = ascRadio.getValue();
						// TODO implement
					}

				});
		return sortButton;
	}

	private Button initSelectButton(final TreePanel treePanel) {
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
		return selectedButton;
	}

	protected void initReloadTool(final TreePanel treePanel,
			final AsyncTreeNode root) {
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
				timer.schedule(1000);
			}
		}, "Refresh"));
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

		// treePanel.getElement().setClassName("activityView");
		return treePanel;
	}

	public TreeNode[] getSelectedNodes() {
		MultiSelectionModel selectionModel = (MultiSelectionModel) treePanel
				.getSelectionModel();
		TreeNode[] nodes = selectionModel.getSelectedNodes();
		for (TreeNode treeNode : nodes) {
			GWT.log("selected nodes" + treeNode.getText());
		}
		return nodes;
	}

}