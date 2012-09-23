package de.unipotsdam.kompetenzmanager.client;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.GlassPanel;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.LiteratureUpdater;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class LiteratureView extends Composite {

	private static LiteratureViewUiBinder uiBinder = GWT
			.create(LiteratureViewUiBinder.class);
	@UiField VerticalPanel LiteratureViewVerticalPanel;
	@UiField Tree literatureTree;
	@UiField AbsolutePanel glassPanelContainer;
	@UiField Button resetButton;
	@UiField Button button;
	
	private GlassPanel glassPanel;
	private ManyToManyConnector dataEntryField;
	public ViewController viewcontroller;
	public Literature storedLiterature;
		
	public HashMap<MyTreeItem,LiteratureEntry> treeEntryMap;
	public HashMap<LiteratureEntry, MyTreeItem> litEntryMap;

	public LiteratureEntryBinder shownLiteratureEntryBinder;

	interface LiteratureViewUiBinder extends UiBinder<Widget, LiteratureView> {
	}

	public LiteratureView()	
	{		
		initWidget(uiBinder.createAndBindUi(this));				
		
		// add glasspanel
		this.glassPanel = new GlassPanel(true);
		this.glassPanel.setVisible(false);
		this.glassPanelContainer.add(glassPanel, 0, 0);
				
	}





	@SuppressWarnings("deprecation")
	public void initLiteratureView() {
		this.shownLiteratureEntryBinder = new LiteratureEntryBinder(this.viewcontroller);
		this.LiteratureViewVerticalPanel.add(shownLiteratureEntryBinder);	
		// load tree
		updateLiteratureView();
	}

	private void updateLiteratureView() {
		GraphBackendImpl backendImpl;
		backendImpl = new GraphBackendImpl(viewcontroller.getWidget());
		LiteratureUpdater<Literature> litUpdater = new LiteratureUpdater<Literature>(this);
		GWT.log("trying to get the literature now");
		backendImpl.getFullLiterature(litUpdater);
		this.shownLiteratureEntryBinder.addThemeButton.setEnabled(false);
	}





	public void addMultiClickMenu(ManyToManyConnector manyToManyConnector) {
		this.glassPanel.setVisible(true);
		this.dataEntryField = manyToManyConnector;
		this.glassPanelContainer.add(dataEntryField);
		
	}

	public void removeMultiClickMenu() {
		if (this.dataEntryField != null) {
			this.glassPanel.setVisible(false);
			this.glassPanelContainer.remove(this.dataEntryField);
			this.dataEntryField = null;
		}		
	}

	public Literature getStoredLiterature() {
		return this.storedLiterature;
	}

	@UiHandler("resetButton")
	void onResetButtonClick(ClickEvent event) {
		updateLiteratureView();
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {		
		clearLiteratureEntry();		
	}


	private void clearLiteratureEntry() {
		this.shownLiteratureEntryBinder = new LiteratureEntryBinder(viewcontroller);
		this.LiteratureViewVerticalPanel.clear();
		this.LiteratureViewVerticalPanel.add(shownLiteratureEntryBinder);
	}



	public void setLiteratureTree(Tree literatureTree) {
		this.literatureTree = literatureTree;
	}



	public Tree getLiteratureTree() {
		return literatureTree;
	}

	public void showLiteratureEntry(MyTreeItem myTreeItem) {
		clearLiteratureEntry();		
		this.LiteratureViewVerticalPanel.clear();
		LiteratureEntryBinder literatureEntryBinder = new LiteratureEntryBinder(viewcontroller);
		literatureEntryBinder.updateSelectedLitEntry(this.treeEntryMap.get(myTreeItem));
		this.LiteratureViewVerticalPanel.add(literatureEntryBinder);
	}





	public void removeTree() {
		this.literatureTree.removeItems();
		
	}
}
