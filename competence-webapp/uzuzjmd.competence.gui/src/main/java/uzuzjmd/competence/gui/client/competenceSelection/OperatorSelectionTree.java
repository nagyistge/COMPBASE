package uzuzjmd.competence.gui.client.competenceSelection;

import uzuzjmd.competence.gui.client.LmsContextFactory;
import uzuzjmd.competence.gui.client.shared.widgets.MyTreePanel;

import com.gwtext.client.core.Connection;
import com.gwtext.client.widgets.tree.XMLTreeLoader;

public class OperatorSelectionTree extends MyTreePanel {

	public OperatorSelectionTree(String databaseConnectionString,
			String rootLabel, String className, Integer width, Integer height,
			String title, LmsContextFactory contextFactory,
			Boolean courseContext) {
		super(databaseConnectionString, rootLabel, className, width, height,
				title, contextFactory, null, courseContext);
	}

	@Override
	protected XMLTreeLoader initXMLLoader() {
		final XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(databaseConnectionString);
		loader.setMethod(Connection.GET);
		loader.setRootTag("operatorRoot");
		loader.setFolderTitleMapping("@name");
		loader.setFolderTag("operator");
		loader.setLeafTitleMapping("@name");
		loader.setLeafTag("operator");
		// loader.setQtipMapping("@treetipp");
		loader.setDisabledMapping("@disabled");
		// loader.setCheckedMapping("isCompulsory");
		// loader.setIconMapping("@icon");
		// loader.setHrefMapping("moodleUrl");
		// loader.setHrefTargetMapping("moodleUrl");
		return loader;
	}

	@Override
	protected Boolean getShowChecked() {
		// TODO Auto-generated method stub
		return null;
	}

}
