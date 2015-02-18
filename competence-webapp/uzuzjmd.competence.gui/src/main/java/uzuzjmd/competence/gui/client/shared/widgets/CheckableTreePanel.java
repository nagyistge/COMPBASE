package uzuzjmd.competence.gui.client.shared.widgets;

import java.util.LinkedList;
import java.util.List;

import uzuzjmd.competence.gui.client.LMSContextFactory;

import com.gwtext.client.widgets.tree.TreeNode;

public abstract class CheckableTreePanel extends MyTreePanel {

	public CheckableTreePanel(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height,
			String title, LMSContextFactory contextFactory) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory);
	}

	public List<String> getCheckedNodes() {
		TreeNode[] checkedNodes = treePanel.getChecked();
		List<String> result = new LinkedList<String>();
		for (int i = 0; i < checkedNodes.length; i++) {
			TreeNode checkedNode = checkedNodes[i];
			result.add(checkedNode.getText());
		}
		return result;
	}

}
