package de.unipotsdam.kompetenzmanager.shared;

import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Literature  implements IsSerializable {
	public Collection<Literature> literatureEntries;
	
	public Literature(Collection<Literature> literaturEntries) {
		this.literatureEntries = literaturEntries;
	}
	public Literature() {		
	}
}

