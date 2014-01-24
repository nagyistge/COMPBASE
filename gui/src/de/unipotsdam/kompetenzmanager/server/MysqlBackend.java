package de.unipotsdam.kompetenzmanager.server;

import java.util.Collection;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.mysql.MysqlConnect;
import de.unipotsdam.kompetenzmanager.server.mysql.VereinfachtesResultSet;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoGetTagsForLabels;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class MysqlBackend implements GraphBackend {

	private MysqlConnect mysqlconnect;

	public MysqlBackend() {
		this.mysqlconnect = new MysqlConnect();
	}

	@Override
	public Graph getFullGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph removeNode(GraphNode targetNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(String fromNode, String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph expandNode(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(Graph graph, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph removeNode(Graph graph, GraphNode targetNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(Graph graph, String fromNode, String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph expandNode(Graph graph, String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph connectNodes(Collection<String> graphNodes, String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Literature getFullLiterature() {
		this.mysqlconnect.connect();
		VereinfachtesResultSet result = this.mysqlconnect
				.issueSelectStatement("select * from literatur");
		Literature literature = convertResultSetToLiteratur(result);
		mysqlconnect.close();
		return literature;

	}

	private Literature convertResultSetToLiteratur(VereinfachtesResultSet result) {
		Literature literature = new Literature();
		while (result.next()) {
			literature.literatureEntries.add(new LiteratureEntry(result
					.getString("titel"), result.getString("author"), result
					.getString("year"), result.getString("abstract"), result
					.getString("paper"), result.getString("volume"), result
					.getInt("id")));
		}
		return literature;
	}

	@Override
	public Literature getLiteratureForTags(Graph graph) {
		Literature literature = new Literature();
		if (graph.nodes.size() > 1) {
			this.mysqlconnect.connect();
			String questionmarkstring = "(";
			Object[] inarray = new String[graph.nodes.size()];
			int inarrayindex = 0;
			for (GraphNode node : graph.nodes) {
				inarray[inarrayindex] = node.getLabel();
				inarrayindex++;
				questionmarkstring += "?,";
			}
			questionmarkstring = questionmarkstring.substring(0,
					questionmarkstring.lastIndexOf(",") - 1); // letzte Komma
																// weg
			questionmarkstring += "?)";
			// System.out.println("Questionmarkstring: " + questionmarkstring +
			// ", arrayindex: " + inarray.length );
			VereinfachtesResultSet result = this.mysqlconnect
					.issueSelectStatement(
							""
									+ "select * from literatur "
									+ "LEFT JOIN literaturfortags ON literatur.id=literaturfortags.literaturid "
									+ "where literaturfortags.tag in "
									+ questionmarkstring, inarray);
			literature = convertResultSetToLiteratur(result);
			mysqlconnect.close();
		}
		return literature;
	}

	@Override
	public Graph getTagsforLiterature(Literature literature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Literature addOrUpdateLiteratureEntry(Literature literatureStored,
			LiteratureEntry literatureEntry) {
		this.mysqlconnect.connect();
		VereinfachtesResultSet result = this.mysqlconnect.issueSelectStatement(
				"select * from literatur where id=?", literatureEntry.id);
		if (result.next()) {
			this.mysqlconnect.issueInsertOrDeleteStatement(
					"delete from literatur where id = ?", literatureEntry.id);
			this.mysqlconnect
					.issueInsertOrDeleteStatement(
							"insert into literatur (id,titel,author,year,paper,volume,abstract) values (?,?,?,?,?,?,?)",
							literatureEntry.id, literatureEntry.titel,
							literatureEntry.author, literatureEntry.year,
							literatureEntry.paper, literatureEntry.volume,
							literatureEntry.abstractText);
		} else {
			this.mysqlconnect
					.issueInsertOrDeleteStatement(
							"insert into literatur (id,titel,author,year,paper,volume,abstract) values (?,?,?,?,?,?,?)",
							literatureEntry.id, literatureEntry.titel,
							literatureEntry.author, literatureEntry.year,
							literatureEntry.paper, literatureEntry.volume,
							literatureEntry.abstractText);
		}
		mysqlconnect.close();
		return new Literature(literatureEntry).mergeWith(literatureStored)
				.intersectStrong(getFullLiterature());
	}

	@Override
	public Literature removeLiteratureEntry(Literature storedLiterature,
			LiteratureEntry literatureEntry) {
		this.mysqlconnect.connect();
		this.mysqlconnect.issueInsertOrDeleteStatement(
				"delete from literatur where id = ?", literatureEntry.id);
		this.mysqlconnect.issueInsertOrDeleteStatement(
				"delete from literaturfortags where literaturid = ?",
				literatureEntry.id);
		mysqlconnect.close();
		return storedLiterature.intersectStrong(getFullLiterature());
	}

	@Override
	public GraphLiteraturePair connectLiteratureToGraph(Literature literature,
			Graph graph) {
		this.mysqlconnect.connect();
		for (LiteratureEntry entry : literature.literatureEntries) {
			entry.graph = graph;
			for (GraphNode node : graph.nodes) {
				this.mysqlconnect
						.issueInsertOrDeleteStatement(
								"insert into literaturfortags (literaturid,tag) values (?,?)",
								entry.id, node.getLabel());
			}
		}

		mysqlconnect.close();
		return new GraphLiteraturePair(graph, literature);
	}

}
