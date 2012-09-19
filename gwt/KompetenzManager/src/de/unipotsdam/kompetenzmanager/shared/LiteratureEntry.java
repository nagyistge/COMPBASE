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
	public String shortName;
	public int id;
	public Graph graph;
	
	public LiteratureEntry( String titel, String author, int year, String abstractText, String paper, String volume, int id) {
		this.titel = titel;
		this.author = author;
		this.year = year;		
		this.abstractText = abstractText;	
		this.paper = paper;
		this.volume = volume;
		this.shortName = this.author+year;
		this.id = id;
		this.graph = new Graph();
	}
	
	public LiteratureEntry() {
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		LiteratureEntry other = (LiteratureEntry) obj;
		return this.id == other.id; 
	}

	@Override
	public int compareTo(LiteratureEntry o) {
		if (o.equals(this)) {
			return 0;
		}
		return this.shortName.compareTo(o.shortName);
	}
	
	@Override
	public String toString() {
		String result = "";
		// TODO Auto-generated method stub
		result+= " " + abstractText;
		result+= " " +author;
		result+= " " +year;
		result+= " " +paper;
		result+= " " +shortName;
		result+= " " +titel;
		result+= " " + volume;
		result+= " " + id;
		return result;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;		
	}
	
}
