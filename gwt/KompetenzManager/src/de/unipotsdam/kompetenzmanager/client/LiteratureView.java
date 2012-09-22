package de.unipotsdam.kompetenzmanager.client;

import java.util.HashMap;

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
	@UiField TreeItem treeHeader;
	
	private LiteratureEntryBinder literatureEntry;
	private GlassPanel glassPanel;
	private MultiClickMenu dataEntryField;
	public ViewController viewcontroller;
	public Literature storedLiterature;
	
	public TreeItem rootItem;
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





	public void initLiteratureView() {
		this.literatureEntry = new LiteratureEntryBinder(this.viewcontroller);
		this.LiteratureViewVerticalPanel.add(literatureEntry);
		this.shownLiteratureEntryBinder = literatureEntry;
		// load tree
		this.rootItem = new TreeItem("Literatur");
		GraphBackendImpl backendImpl;
		backendImpl = new GraphBackendImpl(viewcontroller.getWidget());
		LiteratureUpdater<Literature> litUpdater = new LiteratureUpdater<Literature>(viewcontroller.getLiteratureview(), literatureTree, rootItem);
		GWT.log("trying to get the literature now");
		backendImpl.getFullLiterature(litUpdater);
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

	public Literature getStoredLiterature() {
		return this.storedLiterature;
	}

}
