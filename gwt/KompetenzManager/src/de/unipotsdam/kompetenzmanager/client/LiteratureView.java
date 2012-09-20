package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.widgetideas.client.GlassPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class LiteratureView extends Composite {

	private static LiteratureViewUiBinder uiBinder = GWT
			.create(LiteratureViewUiBinder.class);
	@UiField VerticalPanel LiteratureViewVerticalPanel;
	@UiField Tree literatureTree;
	@UiField AbsolutePanel glassPanelContainer;
	@UiField TreeItem treeHeader;
	private LiteratureEntryBinder literatureEntry;
	private GlassPanel glassPanel;
	private MultiClickMenu dataEntryField;
	public ViewController viewcontroller;
	private Literature storedLiterature;

	interface LiteratureViewUiBinder extends UiBinder<Widget, LiteratureView> {
	}

	public LiteratureView() {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureEntry = new LiteratureEntryBinder(this.viewcontroller);
		this.LiteratureViewVerticalPanel.add(literatureEntry);
		//testlines
		TreeItem treeItem = new TreeItem("test");
		treeItem.addItem(new TreeItem("zweite Ebene"));
		this.treeHeader.addItem(treeItem);
		
		
		// add glasspanel
		this.glassPanel = new GlassPanel(true);
		this.glassPanel.setVisible(false);
		this.glassPanelContainer.add(glassPanel, 0, 0);
	}
	
	

	public void addMultiClickMenu(MultiClickMenu multiClickMenu) {
		this.glassPanel.setVisible(true);
		this.dataEntryField = multiClickMenu;
		this.glassPanelContainer.add(dataEntryField);
		
	}

	public void removeMultiClickMenu() {
		if (this.dataEntryField != null) {
			this.glassPanel.setVisible(false);
			this.glassPanelContainer.remove(this.dataEntryField);
			this.dataEntryField = null;
		}		
	}



	public void update(Literature result) {
		this.storedLiterature = result;
		updateLiteratureTree();
		updateSelectedLitEntry();
	}



	private void updateSelectedLitEntry() {
		// TODO Auto-generated method stub		
	}



	private void updateLiteratureTree() {
		// TODO Auto-generated method stub
		
	}



	public Literature getStoredLiterature() {
		return this.storedLiterature;
	}

}
