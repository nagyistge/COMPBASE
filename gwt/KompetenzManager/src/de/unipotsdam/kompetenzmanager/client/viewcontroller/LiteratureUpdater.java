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

	public LiteratureUpdater(LiteratureView literatureView, Tree literatureTree2, TreeItem literatureTree) {
		this.literatureView = literatureView;
		this.literatureTree = literatureTree;
		this.paperMap = new HashMap<String, MyTreeItem>();		
		this.yearMap = new HashMap<String, MyTreeItem>();
		this.treeEntryMap = new HashMap<MyTreeItem, LiteratureEntry>();
		this.litEntryMap = new HashMap<LiteratureEntry, MyTreeItem>();
		literatureTree2.addItem(literatureTree);	
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
		//storing result
		storeResult(toUpdate);
		//sorting result by alphabetical order
		List<LiteratureEntry> literatureEntries = sortEntries(toUpdate);
		Collections.reverse(literatureEntries);
		//adding Tree Elements
		this.literatureTree.removeItems();		
		addTreeElements(literatureEntries);
		//setting the mapping from treeelements to the literatureentryfield shown
		this.literatureView.treeEntryMap = this.treeEntryMap;
		this.literatureView.litEntryMap = this.litEntryMap;
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
			MyTreeItem paperLevel = new MyTreeItem(literatureEntry.paper);
			MyTreeItem yearLevel = new MyTreeItem(literatureEntry.year);
			MyTreeItem authorLevel = new MyTreeItem(literatureEntry.shortName);
			
			//es gibt das Paper noch nicht
			if (!paperMap.containsKey(paperLevel)) {
				paperLevel.addItem(yearLevel);
				yearLevel.addItem(authorLevel);
				literatureTree.addItem(paperLevel);
				this.paperMap.put(literatureEntry.paper, paperLevel);
			} else {
				paperLevel = this.paperMap.get(paperLevel.getText());
				// es gibt den Jahrgang schon
				if (yearMap.containsKey(literatureEntry.year)) {
					yearLevel = this.yearMap.get(yearLevel.getText());
					yearLevel.addItem(authorLevel);
				// es gibt den Jahrgang noch nicht
				} else {
					this.yearMap.put(literatureEntry.year, yearLevel);
					paperLevel.addItem(yearLevel);
					yearLevel.addItem(authorLevel);
				}				
			}						
			this.treeEntryMap.put(authorLevel, literatureEntry);
			this.litEntryMap.put(literatureEntry,authorLevel);
		}
	}

}
