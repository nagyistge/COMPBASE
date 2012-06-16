package de.kompetenzmanager.model;

public class LabeledElement {

	private String label;

	public LabeledElement(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	@Override
	public String toString() {
		return this.getLabel();
	}
}
