package de.unipotsdam.kompetenzmanager.shared;

import java.text.Collator;
import java.util.Locale;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LiteratureEntry implements IsSerializable,
		Comparable<LiteratureEntry> {

	public String paper;
	public String volume;
	public String titel;
	public String author;
	public String year;
	public String abstractText;
	public String shortName;
	public int id;
	public Graph graph;
	public int klassifikationsnummer = 0;

	public LiteratureEntry(String titel, String author, String year,
			String abstractText, String paper, String volume, int id) {
		this.titel = titel;
		this.author = author;
		this.year = year;
		this.abstractText = abstractText;
		this.paper = paper;
		this.volume = volume;
		this.shortName = this.author + year;
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
		Collator collator = Collator.getInstance(Locale.GERMAN);
		collator.setStrength(Collator.SECONDARY);
		if (o.equals(this)) {
			return 0;
		} else if (collator.compare(o.paper, this.paper) == 0) {
			if (collator.compare(year, o.year) == 0) {
				return collator.compare(shortName, o.shortName);
			} else {
				return collator.compare(year, o.year);
			}
		} else {
			return collator.compare(o.paper, this.paper);
		}
	}

	@Override
	public String toString() {
		String result = "";
		// TODO Auto-generated method stub
		result += " " + abstractText;
		result += " " + author;
		result += " " + year;
		result += " " + paper;
		result += " " + shortName;
		result += " " + titel;
		result += " " + volume;
		result += " " + id;
		return result;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

}
