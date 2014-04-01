package uzuzjmd.up.competence.competence_server;

import java.io.IOException;

import org.apache.jena.fuseki.EmbeddedFusekiServer;
import org.apache.jena.fuseki.server.ServerConfig;

import uzuzjmd.owl.util.CompOntologyManager;

public class FusekiSpike {

	public static void main(String[] args) throws IOException {

		CompOntologyManager compOntologyManager = new CompOntologyManager();
		// compOntologyManager.unregisterReasoner();
		compOntologyManager.begin();

		// DatasetGraph dsg = DatasetGraphFactory.createMem();
		// EmbeddedFusekiServer server = EmbeddedFusekiServer.create(3030,
		// compOntologyManager.getDataset().asDatasetGraph(),
		// MagicStrings.TDBLocation);

		ServerConfig serverconfig = new ServerConfig();
		serverconfig.pages = "I:/workspace/jena-fuseki/pages";

		EmbeddedFusekiServer server = EmbeddedFusekiServer.create(3030,
				compOntologyManager.getDataset().asDatasetGraph(), "comp");

		server.start();

		// // System.out.println("Press enter to exit");
		// // System.in.read();
	}
}
