package de.unipotsdam.kompetenzmanager.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import de.unipotsdam.kompetenzmanager.shared.util.Collator;

public class LiteratureEntry implements IsSerializable,
		Comparable<LiteratureEntry> {

	public String paper;
	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = encode(paper);
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = encode(volume);
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = encode(titel);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = encode(author);
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = encode(year);
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = encode(abstractText);
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = encode(shortName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKlassifikationsnummer() {
		return klassifikationsnummer;
	}

	public void setKlassifikationsnummer(int klassifikationsnummer) {
		this.klassifikationsnummer = klassifikationsnummer;
	}

	public Graph getGraph() {
		return graph;
	}

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
		this.shortName = this.author.substring(0, 3) + year;
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
		Collator collator = new Collator();
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
	
	private String encode(String label2) {
		return label2.replaceAll(" ", "-");
	}

}
