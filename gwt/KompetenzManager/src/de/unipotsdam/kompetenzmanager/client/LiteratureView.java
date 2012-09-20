package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Tree;

public class LiteratureView extends Composite {

	private static LiteratureViewUiBinder uiBinder = GWT
			.create(LiteratureViewUiBinder.class);
	@UiField VerticalPanel LiteratureViewVerticalPanel;
	@UiField TreeItem literatureTreeExample;
	@UiField Tree literatureTree;
	private LiteratureEntryBinder literatureEntry;

	interface LiteratureViewUiBinder extends UiBinder<Widget, LiteratureView> {
	}

	public LiteratureView() {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureEntry = new LiteratureEntryBinder();
		this.LiteratureViewVerticalPanel.add(literatureEntry);
		//testlines
		MyTreeItemUI myTreeItemUI = new MyTreeItemUI();		
		myTreeItemUI.label.setText("Super toller LiteraturEintrag");
		addLiteratureTreeItem(new MyTreeItem(myTreeItemUI));
	}
	
	public void  addLiteratureTreeItem(TreeItem treeItem) {
		this.literatureTree.addItem(treeItem);
	}

}
