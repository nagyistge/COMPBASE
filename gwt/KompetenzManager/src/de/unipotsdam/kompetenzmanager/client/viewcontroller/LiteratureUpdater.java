package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.unipotsdam.kompetenzmanager.client.LiteratureView;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class LiteratureUpdater<L> implements AsyncCallback<L> {

	private LiteratureView literatureView;
	private TreeItem literatureTree;
	private HashMap<TreeItem, LiteratureEntry> treeEntryMap;
	private HashMap<String, TreeItem> paperMap;
	private HashMap<String, TreeItem> yearMap;

	public LiteratureUpdater(LiteratureView literatureView, Tree literatureTree2, TreeItem literatureTree) {
		this.literatureView = literatureView;
		this.literatureTree = literatureTree;
		this.paperMap = new HashMap<String, TreeItem>();		
		this.yearMap = new HashMap<String,TreeItem>();
		this.setTreeEntryMap(new HashMap<TreeItem, LiteratureEntry>());
		literatureTree2.addItem(literatureTree);	
		GWT.log("lit wird geladen ...");
	}

	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Literatur konnte nicht geladen werden", caught);
	}

	@Override
	public void onSuccess(L result) {
		Literature toUpdate = ((Literature) result);
		storeResult(toUpdate);
		List<LiteratureEntry> literatureEntries = sortEntries(toUpdate);
		this.literatureTree.removeItems();		
		addTreeElements(literatureEntries);
		this.literatureView.treeEntryMap = this.treeEntryMap;
		GWT.log("successfully loaded literature from database:" + result.toString());
	}

	public void storeResult(Literature toUpdate) {
		literatureView.storedLiterature = toUpdate;
	}

	public List<LiteratureEntry> sortEntries(Literature toUpdate) {
		List<LiteratureEntry> literatureEntries = new LinkedList<LiteratureEntry>(
				toUpdate.literatureEntries);
		Collections.sort(literatureEntries);
		return literatureEntries;
	}

	public void addTreeElements(List<LiteratureEntry> literatureEntries) {
		for (LiteratureEntry literatureEntry : literatureEntries) {
			TreeItem paperLevel = new TreeItem(literatureEntry.paper);
			TreeItem yearLevel = new TreeItem(literatureEntry.year);
			TreeItem authorLevel = new TreeItem(literatureEntry.shortName);
			if (!paperMap.containsKey(paperLevel)) {																	
				literatureTree.addItem(paperLevel);
				this.paperMap.put(literatureEntry.paper, paperLevel);
			} else {
				paperLevel = this.paperMap.get(paperLevel.getText());
				if (yearMap.containsKey(literatureEntry.year)) {
					yearLevel = this.yearMap.get(literatureEntry.year);														
				} else {
					this.yearMap.put(literatureEntry.year, yearLevel);					
				}				
			}
			paperLevel.addItem(yearLevel);
			yearLevel.addItem(authorLevel);
			this.getTreeEntryMap().put(authorLevel, literatureEntry);
		}
	}

	/**
	 * @param treeEntryMap the treeEntryMap to set
	 */
	private void setTreeEntryMap(HashMap<TreeItem, LiteratureEntry> treeEntryMap) {
		this.treeEntryMap = treeEntryMap;
	}

	/**
	 * @return the treeEntryMap
	 */
	public HashMap<TreeItem, LiteratureEntry> getTreeEntryMap() {
		return treeEntryMap;
	}

	

}
