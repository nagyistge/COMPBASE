package de.unipotsdam.kompetenzmanager.client;

import java.util.UUID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class LiteratureEntryBinder extends Composite {

	private static LiteratureEntryUiBinder uiBinder = GWT
			.create(LiteratureEntryUiBinder.class);
	@UiField ScrollPanel TagsContainer;
	@UiField TextBox titleContent;
	@UiField TextBox authorContent;
	@UiField TextBox publicationDate;
	@UiField Button speichernButton;
	@UiField Button addThemeButton;
	@UiField TextBox paperContent;
	@UiField TextBox volumeContent;
	@UiField TextArea abstractContent;
	@UiField AbsolutePanel klassifikationstabellePanel;
	@UiField Label contentKlassifikationsID;
	private ViewController viewcontroller;
	private LiteratureEntry shownLiteratureEntry;

	interface LiteratureEntryUiBinder extends UiBinder<Widget, LiteratureEntryBinder> {
	}

	public LiteratureEntryBinder(ViewController viewcontroller) {
		initWidget(uiBinder.createAndBindUi(this));
		this.viewcontroller = viewcontroller;
		this.klassifikationstabellePanel.setVisible(false);
	}

	@UiHandler("speichernButton")
	void onSpeichernButtonClick(ClickEvent event) {
		GraphBackendImpl backendImpl = new GraphBackendImpl(viewcontroller.getWidget());		
		this.shownLiteratureEntry = aggregateLiteratureEntry();
		backendImpl.addOrUpdateLiteratureEntry(viewcontroller.getLiteratureview().getStoredLiterature(),shownLiteratureEntry , new UpdateLiteratureView(viewcontroller.getLiteratureview()));
		if (this.shownLiteratureEntry.klassifikationsnummer > 0) {
			this.contentKlassifikationsID.setText(this.shownLiteratureEntry.klassifikationsnummer+"");
			this.klassifikationstabellePanel.setVisible(true);
		}
		this.speichernButton.setEnabled(false);
	}

	private LiteratureEntry aggregateLiteratureEntry() {
		int id = 0;
		if (this.shownLiteratureEntry != null) {
		  this.shownLiteratureEntry.abstractText = abstractContent.getText();
		  this.shownLiteratureEntry.author = abstractContent.getText();
		  this.shownLiteratureEntry.paper = paperContent.getText();
		  this.shownLiteratureEntry.year = this.publicationDate.getText();
		  this.shownLiteratureEntry.volume = this.volumeContent.getText();
		  this.shownLiteratureEntry.titel =  this.titleContent.getText();		  
		  this.shownLiteratureEntry.shortName = this.shownLiteratureEntry.author+this.shownLiteratureEntry.year;
		} else {
			id = UUID.randomUUID().hashCode();
			return new LiteratureEntry(this.titleContent.getText(), this.authorContent.getText(), this.publicationDate.getText(), this.abstractContent.getText(), this.paperContent.getText(),this.volumeContent.getText(), id);
		}
		return shownLiteratureEntry;		
		
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
}