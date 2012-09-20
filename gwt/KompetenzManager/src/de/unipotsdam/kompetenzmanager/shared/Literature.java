package de.unipotsdam.kompetenzmanager.shared;

import java.util.HashSet;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Literature  implements IsSerializable {
	public HashSet<LiteratureEntry> literatureEntries;
	
	public Literature(HashSet<LiteratureEntry> literaturEntries) {
		this.literatureEntries = literaturEntries;
	}
	public Literature(LiteratureEntry ... newEntries) {
		this.literatureEntries = new HashSet<LiteratureEntry>();		
		for (LiteratureEntry literatureEntry : newEntries) {
			literatureEntries.add(literatureEntry);
		}
	}
	
	public Literature() {	
		this.literatureEntries = new HashSet<LiteratureEntry>();
	}
	
	public Literature intersectWith(Literature literatureStored) {
		this.literatureEntries.retainAll(literatureStored.literatureEntries);
		this.literatureEntries.addAll(literatureStored.literatureEntries);
		return this;
	}
	@Override
	public boolean equals(Object obj) {
		Literature other = (Literature) obj;
		return this.literatureEntries.equals(other.literatureEntries); 
	}
	
	@Override
	public String toString() {
		String result = "";
		for (LiteratureEntry element : this.literatureEntries) {
			result += "LiteratureEntry: " + element.toString() + "\n";
		}
		return result;		
	}
}

