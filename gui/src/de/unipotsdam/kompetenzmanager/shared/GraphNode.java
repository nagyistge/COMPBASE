package de.unipotsdam.kompetenzmanager.shared;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.IsSerializable;

public class GraphNode implements IsSerializable {

	public GraphNode() {
	}

	public GraphNode(String label) {
		this.label = label;
	}

	public String label;

	@Override
	public boolean equals(Object obj) {
		return (((GraphNode) obj).label.equals(label));
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(label.codePointAt(0));
	}
	@Override
	public String toString() {
		return label;
	}
}
