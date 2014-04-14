package uzuzjmd.competence.gui.client;

import javax.swing.tree.DefaultTreeModel;

import com.google.gwt.user.client.ui.Tree;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public class ActivityPanel extends Tree {
	public ActivityPanel(DefaultTreeModel activityEntries) {
		TreeNode treeNode = new AsyncTreeNode("hello");
		final XMLTreeLoader loader = new XMLTreeLoader();
	}
}
