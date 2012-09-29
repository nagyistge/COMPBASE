package de.unipotsdam.kompetenzmanager.server;

import java.io.IOException;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackend;

public class GraphBackendFactory {
	private static Neo4JGraphBackendImpl neo4jGraphBackendImpl;
	
	public synchronized GraphBackend createInstance() {
//		return new DummyBackendImpl();
		if (neo4jGraphBackendImpl == null) {
			try {
				neo4jGraphBackendImpl = new Neo4JGraphBackendImpl();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return neo4jGraphBackendImpl;
		
				
	}
}
