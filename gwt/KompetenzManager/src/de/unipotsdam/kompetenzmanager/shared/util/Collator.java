package de.unipotsdam.kompetenzmanager.shared.util;

public class Collator {

	public static final Collator getInstance() {
		return instance;
	}

	private static final Collator instance = new Collator();

	public int compare(String string, String string2) {
		return string.toLowerCase().compareTo(string2.toLowerCase());
	}
	
}
