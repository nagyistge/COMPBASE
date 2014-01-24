package de.unipotsdam.kompetenzmanager.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GraphNode implements IsSerializable {

	public GraphNode() {
	}

	public GraphNode(String label) {
		this.setLabel(label);
	}

	private String label;

	@Override
	public boolean equals(Object obj) {
		return (((GraphNode) obj).getLabel().equals(getLabel()));
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(getLabel().codePointAt(0));
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = encode(label);
	}

	private String encode(String label2) {
		return label2.replaceAll(" ", "-");
	}
}
