package de.unipotsdam.kompetenzmanager.shared;

import java.util.Collection;
import java.util.HashSet;

import com.google.gwt.user.client.rpc.IsSerializable;


public class LiteratureEntry implements IsSerializable, Comparable<LiteratureEntry>{
	
	public String paper;
	public String volume;
	public String titel;
	public String author;
	public int year;
	public String abstractText;	
	public Collection<GraphNode> tags;
	public int keyOfSuperClass;
	public Collection<LiteratureEntry> subClasses;
	public String shortName;
	
	public LiteratureEntry( String titel, String author, int year, String abstractText, Collection<GraphNode> tags, int keyOfSuperClass, String paper, String volume) {
		this.titel = titel;
		this.author = author;
		this.year = year;
		this.tags = tags;
		this.abstractText = abstractText;
		this.keyOfSuperClass = keyOfSuperClass;
		this.subClasses = new HashSet<LiteratureEntry>();
		this.shortName = this.author+=year;
	}
	
	public LiteratureEntry() {
	}
	
	@Override
	public int hashCode() {
		return this.abstractText.hashCode() + this.titel.hashCode()+ this.year;
	}
	
	@Override
	public boolean equals(Object obj) {
		LiteratureEntry other = (LiteratureEntry) obj;
		return this.abstractText.equals(other.abstractText) && this.titel.equals(other.titel) && this.year == other.year; 
	}

	@Override
	public int compareTo(LiteratureEntry o) {
		return this.shortName.compareTo(o.shortName);
	}
	
}
