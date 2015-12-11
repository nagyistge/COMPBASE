package uzuzjmd.competence.main;

import java.io.IOException;

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;

public class FusekiServer {

	public static void main(String[] args) throws IOException {
		startServer();
	}

	public static void startServer() {
		CompOntologyManager compOntologyManager = new CompOntologyManagerJenaImpl();
		startFuseki(compOntologyManager);
	}

	/**
	 * TODO fix / implement fuseki server
	 * 
	 * @param compOntologyManager
	 */
	public static void startFuseki(CompOntologyManager compOntologyManager) {
		// compOntologyManager.begin();
		//
		// EmbeddedFusekiServer server = EmbeddedFusekiServer.create(3030,
		// compOntologyManager.getDataset().asDatasetGraph(), "comp");
		// server.start();
	}

}
