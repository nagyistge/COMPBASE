package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class GraphBackendImpl implements GraphBackendAsync {
	
	private ShowCompetenceBinder2 showCompetenceBinder;

	public GraphBackendImpl(ShowCompetenceBinder2 showCompetenceBinder) {
		this.showCompetenceBinder = showCompetenceBinder;
	}

	/**
	 * Create a remote service proxy to talk to the server-side service.
	 */
	private final GraphBackendAsync graphBackEnd = GWT
			.create(GraphBackend.class);

	@Override
	public void getFullGraph(AsyncCallback<Graph> graph) {
		graphBackEnd.getFullGraph(new GraphUpdater<Graph>(showCompetenceBinder));
	}

	@Override
	public void addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel, AsyncCallback<Graph> callback) {
		if (callback == null) {
			callback = new GraphUpdater<Graph>(showCompetenceBinder);
		}
		graphBackEnd.addNode(sourceNode, newNode, kantenLabel, callback);
	}

	@Override
	public void findShortestPath(String keyword, AsyncCallback<Graph> callback) {
		if (callback == null) {
			callback = new GraphUpdater<Graph>(showCompetenceBinder);
		}
		graphBackEnd.findShortestPath(keyword, new GraphUpdater<Graph>(showCompetenceBinder));
	}

	@Override
	public void removeNode(GraphNode targetNode, AsyncCallback<Graph> callback) {
		if (callback == null) {
			callback = new GraphUpdater<Graph>(showCompetenceBinder);
		}
		graphBackEnd.removeNode(targetNode, callback);
	}

}
