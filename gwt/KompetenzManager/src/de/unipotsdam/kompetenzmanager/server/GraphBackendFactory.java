package de.unipotsdam.kompetenzmanager.server;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackend;

public class GraphBackendFactory {
	private static Neo4JGraphBackendImpl neo4jGraphBackendImpl;
	
	public synchronized GraphBackend createInstance() {
//		return new DummyBackendImpl();
		if (neo4jGraphBackendImpl == null) {
			neo4jGraphBackendImpl = new Neo4JGraphBackendImpl();
		}
		return neo4jGraphBackendImpl;
		
				
	}
}
