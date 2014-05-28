package uzuzjmd.competence.service.rest.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GraphTriple implements IsSerializable {
	
	public GraphTriple() {}
	
	public GraphTriple(String fromNode, String toNode, String label, Boolean directed) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.label = label;
		this.directed = directed;
	}
	
	public String fromNode;
	public String toNode;
	public String label;
	public Boolean directed;
	
	@Override
	public boolean equals(Object obj) {
		return (((GraphTriple)obj).fromNode.equals(fromNode))
		&& ((GraphTriple)obj).toNode.equals(toNode)
//		&& ((GraphTriple)obj).label.equals(label);
		&& ((GraphTriple)obj).directed == directed;		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return fromNode.hashCode() + toNode.hashCode() + directed.hashCode();
	}	
	
	@Override
	public String toString() {
		return "fromNode: " + fromNode + ", toNode: " + toNode + ", Label: " + label + ", directed:" + directed;
	}
}
