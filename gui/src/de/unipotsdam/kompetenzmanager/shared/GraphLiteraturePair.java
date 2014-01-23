package de.unipotsdam.kompetenzmanager.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GraphLiteraturePair implements IsSerializable {
	public Graph graph;
	public Literature literature;
	public GraphLiteraturePair(Graph graph, Literature literature) {
		this.graph = graph;
		this.literature = literature;
	}
	
	public GraphLiteraturePair() {		
	}
}
