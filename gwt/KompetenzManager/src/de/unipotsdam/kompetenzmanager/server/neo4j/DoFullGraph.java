package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.ArrayList;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.TraversalPosition;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFullGraph extends DoNeo {

	public DoFullGraph(GraphDatabaseService graphDB, Index<Node> nodeIndex) {
		super(graphDB, nodeIndex);
	}

	@Override
	public Graph doit() {				
		
//		Node relTable = this.nodeIndex.get(TABLE_KEY, "reltable").getSingle();
//		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
//		for (String relKey: relTable.getPropertyKeys()) {
//			relationships.add((Relationship) this.nodeIndex.get(REL_KEY, relKey).getSingle());
//		}		
//		return convertRelationShipsToGraph(relationships);
		
		StopEvaluator stopEvaluator = new StopEvaluator() {
			@Override
			public boolean isStopNode(TraversalPosition currentPos) {
				return currentPos.returnedNodesCount() > maxDepth;
			}
		};
		ReturnableEvaluator returnableEvaluator = new ReturnableEvaluator() {
			@Override
			public boolean isReturnableNode(TraversalPosition currentPos) {
				return true;
			}
		};
		
				
		
//		Traverser traverserAssociatedWith = createTraverser(stopEvaluator,
//				returnableEvaluator, RelTypes.assoziatedWith);
		Traverser traverserSubClassOf = createTraverser(stopEvaluator, returnableEvaluator, RelTypes.subclassOf);
//		Graph result = traverseGraph(traverserAssociatedWith);
		Graph resultPartTwo = traverseGraph(traverserSubClassOf);
//		result.mergeWith(resultPartTwo);
//		return result;
		return resultPartTwo;
	}

	

}
