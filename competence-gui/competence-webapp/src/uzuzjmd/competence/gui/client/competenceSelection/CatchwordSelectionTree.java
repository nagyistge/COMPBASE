package uzuzjmd.competence.gui.client.competenceSelection;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.widgets.MyTreePanel;

import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public class CatchwordSelectionTree extends MyTreePanel {

	public CatchwordSelectionTree(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height,
			String title, ContextFactory contextFactory) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected XMLTreeLoader initXMLLoader() {
		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(databaseConnectionString);
		loader.setMethod(Connection.GET);
		loader.setRootTag("catchwordRoot");
		loader.setFolderTitleMapping("@name");
		loader.setFolderTag("catchword");
		loader.setLeafTitleMapping("@name");
		loader.setLeafTag("catchword");
		loader.setQtipMapping("@qtip");
		loader.setDisabledMapping("@disabled");
		// loader.setCheckedMapping("isCompulsory");
		// loader.setIconMapping("@icon");
		// loader.setHrefMapping("moodleUrl");
		// loader.setHrefTargetMapping("moodleUrl");
		return loader;
	}

}
