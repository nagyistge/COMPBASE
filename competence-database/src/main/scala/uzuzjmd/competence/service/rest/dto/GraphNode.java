package uzuzjmd.competence.service.rest.dto;

public class GraphNode {

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
