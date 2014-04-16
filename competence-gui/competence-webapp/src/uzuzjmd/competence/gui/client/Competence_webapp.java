package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.Function;
import com.gwtext.client.core.Template;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.TreeEditor;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");

		Panel panel1 = new Panel();
		ActivityEntry activitiesEntry = new ActivityEntry(
				"http://localhost/moodle/mod/assign/view.php?id=6",
				"assignment", "just an assignmen");
		panel1.add(activitiesEntry);

		container.add(panel1);

		ActivityTooltip activitiesTooltip = new ActivityTooltip(
				"http://localhost/moodle/mod/assign/view.php?id=6",
				"assignmentpreview");

		activitiesTooltip.init(panel1);
		
		
		// test gwt ext
		
		
		Panel panel = new Panel();  
        panel.setBorder(false);  
        panel.setPaddings(15);  
  
//        final Store store = new SimpleStore(new String[]{"abbr", "activities "}, getCountries());  
//        store.load();  
  
        final Template template = new Template("<div class=\"x-combo-list-item\">" +  
                "<img src=\"images/flags/{abbr}.gif\"> " +  
                "{activities }<div class=\"x-clear\"></div></div>");  
  
//        ComboBox cb = new ComboBox();  
//        cb.setMinChars(1);  
//        cb.setFieldLabel("Countries");  
//        cb.setStore(store);  
//        cb.setDisplayField("activities ");  
//        cb.setMode(ComboBox.LOCAL);  
//        cb.setTriggerAction(ComboBox.ALL);  
//        cb.setEmptyText("Bitte Aktivitäten auswählen ");  
//        cb.setTypeAhead(true);  
//        cb.setSelectOnFocus(true);  
//        cb.setWidth(60);  
//        cb.setResizable(true);  
//        cb.setTpl(template);  
//        cb.setTitle("Activitäten");  
//        cb.setAllowBlank(false);  
  
        final TreePanel treePanel = new TreePanel();  
        treePanel.setWidth(240);  
        treePanel.setHeight(400);  
        treePanel.setTitle("Aktivitätsübersicht");  
        treePanel.setAnimate(true);  
        treePanel.setEnableDD(true);  
        treePanel.setContainerScroll(true);  
        treePanel.setRootVisible(false);  
  
  
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
        loader.setAttributeMappings(new String[]{"@url"});  
  
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
                timer.schedule(1000);  
            }  
        }, "Refresh"));  
  
//        TreeEditor treeEditor = new TreeEditor(treePanel, cb);  
  
        panel = new Panel();  
        panel.setBorder(false);  
        panel.add(treePanel);  
  
        container.add(panel);  
    }
//	private Object[][] getCountries() {
//		return new Object[][] {
//				new Object[] { "au", "Australia", "Canberra", "Australia",
//						new Integer(18090000), new Integer(7713360) },
//				new Object[] { "br", "Brazil", "Brasilia", "South America",
//						new Integer(170000000), new Integer(8547404) },
//				new Object[] { "ca", "Canada", "Ottawa", "North America",
//						new Integer(31000000), new Integer(9976140) },
//				new Object[] { "cn", "China", "Beijing", "Asia",
//						new Integer(1222017000), new Integer(9596960) },
//				new Object[] { "de", "Germany", "Berlin", "Europe",
//						new Integer(80942000), new Integer(356910) },
//				new Object[] { "fr", "France", "Paris", "Europe",
//						new Integer(57571000), new Integer(551500) },
//				new Object[] { "in", "India", "New Delhi", "Asia",
//						new Integer(913747000), new Integer(3287590) },
//				new Object[] { "sc", "Seychelles", "Victoria", "Africa",
//						new Integer(73000), new Integer(280) },
//				new Object[] { "us", "United States", "Washington, DC",
//						"North America", new Integer(260513000),
//						new Integer(9372610) },
//				new Object[] { "jp", "Japan", "Tokyo", "Asia",
//						new Integer(125422000), new Integer(377800) },
//				new Object[] { "ie", "Italy", "Rome", "Eorope",
//						new Integer(57867000), new Integer(301270) },
//				new Object[] { "gh", "Ghana", "Accra", "Africa",
//						new Integer(16944000), new Integer(238540) },
//				new Object[] { "ie", "Iceland", "Reykjavik", "Europe",
//						new Integer(270000), new Integer(103000) },
//				new Object[] { "fi", "Finland", "Helsinki", "Europe",
//						new Integer(5033000), new Integer(338130) },
//				new Object[] { "ch", "Switzerland", "Berne", "Europe",
//						new Integer(6910000), new Integer(41290) } };
//	}

}
