package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.LiteratureUpdater;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.util.UUID;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class LiteratureEntryBinder extends Composite {

	private static LiteratureEntryUiBinder uiBinder = GWT
			.create(LiteratureEntryUiBinder.class);
	@UiField ScrollPanel TagsContainer;
	@UiField TextArea titleContent;
	@UiField TextBox authorContent;
	@UiField TextBox publicationDate;
	@UiField Button speichernButton;
	@UiField Button addThemeButton;
	@UiField TextBox paperContent;
	@UiField TextBox volumeContent;
	@UiField TextArea abstractContent;
	@UiField AbsolutePanel klassifikationstabellePanel;
	@UiField Label contentKlassifikationsID;
	@UiField FlowPanel verticalTagPanel;
	@UiField Button deleteButton;
	private ViewController viewcontroller;
	public LiteratureEntry shownLiteratureEntry;

	interface LiteratureEntryUiBinder extends UiBinder<Widget, LiteratureEntryBinder> {
	}

	public LiteratureEntryBinder(ViewController viewcontroller) {
		initWidget(uiBinder.createAndBindUi(this));
		this.viewcontroller = viewcontroller;
		this.klassifikationstabellePanel.setVisible(false);
//		this.addThemeButton = new Button("add Theme");
	}

	@UiHandler("speichernButton")
	void onSpeichernButtonClick(ClickEvent event) {
//		GraphBackendImpl backendImpl = new GraphBackendImpl(viewcontroller.getWidget());		
		//reading all the text fields
		this.shownLiteratureEntry = aggregateLiteratureEntry();
		
		//storing them in the db and updating tree
		LiteratureView litView = viewcontroller.getLiteratureview();
		LiteratureUpdater<Literature> litUpdater = new LiteratureUpdater<Literature>(this.viewcontroller.getLiteratureview());
		viewcontroller.getBackendImpl().addOrUpdateLiteratureEntry(viewcontroller.getLiteratureview().getStoredLiterature(),shownLiteratureEntry,litUpdater);		

		//showing the klassifkation if possible
		if (this.shownLiteratureEntry.klassifikationsnummer > 0) {
			this.contentKlassifikationsID.setText(this.shownLiteratureEntry.klassifikationsnummer+"");
			this.klassifikationstabellePanel.setVisible(true);
		}
		
		//disabling button
		this.speichernButton.setEnabled(false);		
		
		//selecting the correct treeitem
		if (this.viewcontroller.getLiteratureview().litEntryMap.isEmpty()) {
			GWT.log("litEntry Map does nocht contain any values");
		}
		TreeItem treeItem = this.viewcontroller.getLiteratureview().litEntryMap.get(this.shownLiteratureEntry);
		if (treeItem != null) {			
			treeItem.setState(true);
		} else {
			GWT.log("after saving the correct literature entry could not be selected because it does not exist in the literatureEntry Map\n \n" + this.shownLiteratureEntry.toString());
		}
	}

	private LiteratureEntry aggregateLiteratureEntry() {
		int id = 0;
		if (this.shownLiteratureEntry != null) {
		  this.shownLiteratureEntry.abstractText = abstractContent.getText();
		  this.shownLiteratureEntry.author = authorContent.getText();
		  this.shownLiteratureEntry.paper = paperContent.getText();
		  this.shownLiteratureEntry.year = this.publicationDate.getText();
		  this.shownLiteratureEntry.volume = this.volumeContent.getText();
		  this.shownLiteratureEntry.titel =  this.titleContent.getText();		  
		  this.shownLiteratureEntry.shortName = this.shownLiteratureEntry.author+this.shownLiteratureEntry.year;
		} else {
			id = UUID.uuid().hashCode();
			return new LiteratureEntry(this.titleContent.getText(), this.authorContent.getText(), this.publicationDate.getText(), this.abstractContent.getText(), this.paperContent.getText(),this.volumeContent.getText(), id);
		}
		return shownLiteratureEntry;		
		
	}
	
	public void updateSelectedLitEntry(LiteratureEntry lit) {
		this.shownLiteratureEntry = lit;
		  abstractContent.setText(this.shownLiteratureEntry.abstractText);
		  authorContent.setText(this.shownLiteratureEntry.author);
		  paperContent.setText(this.shownLiteratureEntry.paper);
		  publicationDate.setText(this.shownLiteratureEntry.year);
		  this.volumeContent.setText(this.shownLiteratureEntry.volume);
		  titleContent.setText(this.shownLiteratureEntry.titel);
//		  this.TagsContainer.clear();
		  for (GraphNode graph : lit.graph.nodes) {			  
			  MyButton label = new MyButton(graph.label, viewcontroller);
			  this.verticalTagPanel.add(label);
		  }
		  this.addThemeButton.setEnabled(true);
	}
	
	@UiHandler("titleContent")
	void onTitleContentChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("authorContent")
	void onAuthorContentChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("publicationDate")
	void onPublicationDateChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("paperContent")
	void onPaperContentChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("volumeContent")
	void onVolumeContentChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("abstractContent")
	void onAbstractContentChange(ChangeEvent event) {
		this.speichernButton.setEnabled(true);
	}
	@UiHandler("addThemeButton")
	void onAddThemeButtonClick(ClickEvent event) {
		this.viewcontroller.addThemeTag(this.shownLiteratureEntry);
	}
	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		this.viewcontroller.deleteLitEntry(this.shownLiteratureEntry);
	}
}
