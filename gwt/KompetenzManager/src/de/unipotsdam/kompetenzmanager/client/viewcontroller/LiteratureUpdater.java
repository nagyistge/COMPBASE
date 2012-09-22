package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.unipotsdam.kompetenzmanager.client.LiteratureView;
import de.unipotsdam.kompetenzmanager.client.MyTreeItem;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class LiteratureUpdater<L> implements AsyncCallback<L> {

	private LiteratureView literatureView;
	private TreeItem literatureTree;
	private HashMap<MyTreeItem, LiteratureEntry> treeEntryMap;
	private HashMap<String, MyTreeItem> paperMap;
	private HashMap<String, MyTreeItem> yearMap;
	private HashMap<LiteratureEntry,MyTreeItem> litEntryMap;

	public LiteratureUpdater(LiteratureView literatureView) {
		this.literatureView = literatureView;
		//adding root node
		this.literatureTree = new TreeItem("Literatur");
		literatureView.removeTree();
		Tree litTree = literatureView.getLiteratureTree();		
		litTree.addItem(literatureTree);	

		this.paperMap = new HashMap<String, MyTreeItem>();		
		this.yearMap = new HashMap<String, MyTreeItem>();
		this.treeEntryMap = new HashMap<MyTreeItem, LiteratureEntry>();
		this.litEntryMap = new HashMap<LiteratureEntry, MyTreeItem>();		
		GWT.log("lit wird geladen ...");
	}

	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Literatur konnte nicht geladen werden", caught);
	}

	@Override
	public void onSuccess(L result) {
		//casting
		Literature toUpdate = ((Literature) result);
		
		GWT.log(toUpdate.literatureEntries.size() + "Eintrage"+ toUpdate.toString());
		
		//storing result
		storeResult(toUpdate);
		//sorting result by alphabetical order
		List<LiteratureEntry> literatureEntries = sortEntries(toUpdate);
		Collections.reverse(literatureEntries);				
		
		//adding Tree Elements
		this.literatureTree.removeItems();		
		addTreeElements(literatureEntries);
//		removeDuplicateElements();
		//setting the mapping from treeelements to the literatureentryfield shown
		this.literatureView.treeEntryMap = this.treeEntryMap;
		this.literatureView.litEntryMap = this.litEntryMap;
		GWT.log("successfully loaded literature from database:" + result.toString());		
	}

//	private void removeDuplicateElements() {
//		HashMap<String, MyTreeItem> hashSet = new HashMap<String, MyTreeItem>();
//		for (int i =0; i< literatureTree.asTreeItem().getChildCount();i++) {
//			MyTreeItem paperItem = (MyTreeItem) literatureTree.asTreeItem().getChild(i);
//			for (int j=0;j<paperItem.getChildCount();j++) {
//				MyTreeItem yearItem = (MyTreeItem) paperItem.getChild(j);
//				String key = yearItem.getText()+paperItem.getText();
//				if (hashSet.containsKey(key)) {
//					for (int k=0;k<yearItem.getChildCount();k++) {
//						hashSet.get(key).addItem(yearItem.getChild(k));						
//					}
//					paperItem.removeItem(yearItem);
//				} else {
//					hashSet.put(key, yearItem);
//				}								
//			}
//		}
		
//	}

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
			if (literatureEntry.paper.trim().equals("")) {
				literatureEntry.paper = "keine Angabe";
			}
			if (literatureEntry.year.trim().equals("")) {
				literatureEntry.year = "keine Angabe";
			}	
			MyTreeItem paperLevel = new MyTreeItem(literatureEntry.paper, literatureView);
			MyTreeItem yearLevel = new MyTreeItem(literatureEntry.year, literatureView);
			MyTreeItem authorLevel = new MyTreeItem(literatureEntry.shortName, literatureView);
			
			//es gibt das Paper noch nicht
			if (!paperMap.containsKey(literatureEntry.paper)) {
				paperLevel.addItem(yearLevel);
				yearLevel.addItem(authorLevel);
				literatureTree.addItem(paperLevel);
				this.paperMap.put(literatureEntry.paper, paperLevel);
				this.yearMap.put(literatureEntry.year, yearLevel);
			} else {
				paperLevel = this.paperMap.get(literatureEntry.paper);				
				if (!yearMap.containsKey(literatureEntry.year)) {
					paperLevel.addItem(yearLevel);
					yearLevel.addItem(authorLevel);				
					this.yearMap.put(literatureEntry.year, yearLevel);
				} else {
					yearLevel = this.yearMap.get(yearLevel.paper);
					yearLevel.addItem(authorLevel);
				}				
			}						
			this.treeEntryMap.put(authorLevel, literatureEntry);
			this.litEntryMap.put(literatureEntry,authorLevel);
		}
	}

}
