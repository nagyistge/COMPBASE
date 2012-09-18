package de.unipotsdam.kompetenzmanager.shared;

import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;


public class LiteratureEntry implements IsSerializable{
	
	
	public String titel;
	public String author;
	public int year;
	public String abstractText;
	public int id;
	public Collection<GraphNode> tags;
	
	public LiteratureEntry( String titel, String author, int year, String abstractText, int id, Collection<GraphNode> tags ) {
		this.titel = titel;
		this.author = author;
		this.year = year;
		this.tags = tags;
		this.abstractText = abstractText;
	}
	
	public LiteratureEntry() {
	}
	
}
