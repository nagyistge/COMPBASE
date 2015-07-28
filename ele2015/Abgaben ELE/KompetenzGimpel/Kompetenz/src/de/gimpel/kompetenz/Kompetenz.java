package de.gimpel.kompetenz;

public class Kompetenz {
	private String name = null;
	private boolean selected = false;

	public Kompetenz(String name, boolean selected) {
		super();
		this.name = name;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}