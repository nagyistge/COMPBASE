package uzuzjmd.competence.shared.dto;

import java.util.HashMap;
import java.util.List;

public class LearningTemplateResultSet {
	private GraphNode root; // root is set if graph consists of one node
	private Graph resultGraph;
	private HashMap<GraphTriple, List<String>> catchwordMap;
	private String nameOfTheLearningTemplate;

	public LearningTemplateResultSet() {

	}

	public LearningTemplateResultSet(GraphNode root) {
		this.root = root;
	}

	public LearningTemplateResultSet(Graph resultGraph,
			HashMap<GraphTriple, List<String>> catchwordMap,
			String nameOfTheLearningTemplate) {
		super();
		this.resultGraph = resultGraph;
		this.catchwordMap = catchwordMap;
		this.nameOfTheLearningTemplate = nameOfTheLearningTemplate;
	}

	public Graph getResultGraph() {
		return resultGraph;
	}

	public void setResultGraph(Graph resultGraph) {
		this.resultGraph = resultGraph;
	}

	public HashMap<GraphTriple, List<String>> getCatchwordMap() {
		return catchwordMap;
	}

	public void setCatchwordMap(HashMap<GraphTriple, List<String>> catchwordMap) {
		this.catchwordMap = catchwordMap;
	}

	public String getNameOfTheLearningTemplate() {
		return nameOfTheLearningTemplate;
	}

	public void setNameOfTheLearningTemplate(String nameOfTheLearningTemplate) {
		this.nameOfTheLearningTemplate = nameOfTheLearningTemplate;
	}

	public GraphNode getRoot() {
		return root;
	}

	public void setRoot(GraphNode root) {
		this.root = root;
	}

}
