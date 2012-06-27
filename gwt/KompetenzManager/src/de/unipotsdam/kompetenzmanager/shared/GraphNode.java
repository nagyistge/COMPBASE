package de.unipotsdam.kompetenzmanager.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GraphNode implements IsSerializable{
	
	public GraphNode() {}
	
	public GraphNode(String label) {
		this.label = label;
	} 
	public String label;
	
	@Override
	public boolean equals(Object obj) {
		return (((GraphNode)obj).label.equals(label));
	}
}
