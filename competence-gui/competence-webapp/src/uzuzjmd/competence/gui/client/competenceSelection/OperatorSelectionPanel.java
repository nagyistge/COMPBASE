package uzuzjmd.competence.gui.client.competenceSelection;

import uzuzjmd.competence.gui.shared.MyTreePanel;

import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public class OperatorSelectionPanel extends MyTreePanel {

	public OperatorSelectionPanel(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height, String title) {
		super(databaseConnectionString, rootLabel, className, width, height, title);
	}

	@Override
	protected XMLTreeLoader initXMLLoader() {
		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(databaseConnectionString);
		loader.setMethod(Connection.GET);
		loader.setRootTag("operatorXMLTrees");
		loader.setFolderTitleMapping("@name");
		loader.setFolderTag("operator");
		loader.setLeafTitleMapping("@name");
		loader.setLeafTag("operator");
		loader.setQtipMapping("@qtip");
		loader.setDisabledMapping("@disabled");
		// loader.setCheckedMapping("isCompulsory");
		// loader.setIconMapping("@icon");
		// loader.setHrefMapping("moodleUrl");
		// loader.setHrefTargetMapping("moodleUrl");
		return loader;
	}

}
