package de.unipotsdam.kompetenzmanager.shared;

import java.util.HashSet;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Literature  implements IsSerializable {
	public HashSet<LiteratureEntry> literatureEntries;
	
	public Literature(HashSet<LiteratureEntry> literaturEntries) {
		this.literatureEntries = literaturEntries;
	}
	public Literature() {		
	}
	
	public Literature intersectWith(Literature literatureStored) {
		this.literatureEntries.retainAll(literatureStored.literatureEntries);
		this.literatureEntries.addAll(literatureStored.literatureEntries);
		return this;
	}
}

