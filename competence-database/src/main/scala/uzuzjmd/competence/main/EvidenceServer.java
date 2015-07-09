package uzuzjmd.competence.main;

import java.io.IOException;

import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.ResponseCorsFilter;
import uzuzjmd.competence.service.rest.client.dto.AbstractTreeEntry;
import uzuzjmd.competence.service.rest.client.dto.AbstractXMLTree;
import uzuzjmd.competence.service.rest.client.dto.ActivityEntry;
import uzuzjmd.competence.service.rest.client.dto.ActivityTyp;
import uzuzjmd.competence.service.rest.client.dto.UserTree;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class EvidenceServer {

	public static void main(String[] args) throws IllegalArgumentException, NullPointerException, IOException {
		String adminname = args[0];
		String adminpassword = args[1];
		MagicStrings.MOODLEURL = args[args.length - 1];
		startServer(adminname, adminpassword);
	}

	private static void startServer(String adminlogin, String adminloginpassword) throws IOException {
		publishServer();
	}

	private static void publishServer() throws IOException {

		// publishSoapServer(evidenceServiceImpl);
		publishRestServer();

		System.out.println("Press enter to exit");
		System.in.read();

		System.exit(1);
	}

	private static void publishRestServer() throws IOException {
		ResourceConfig resourceConfig = new DefaultResourceConfig(EvidenceServiceRestServerImpl.class, UserTree.class, AbstractTreeEntry.class, AbstractXMLTree.class, ActivityTyp.class,
				ActivityEntry.class);
		resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
		GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURL, resourceConfig);
		System.out.println("publishing rest server to to " + MagicStrings.RESTURL);
		System.out.println("Test this with2: " + MagicStrings.RESTURL + "/lms" + "/activities/usertree/xml/{groupId bzw. Kursid}");
	}

	// private static void publishSoapServer(final EvidenceService
	// evidenceServiceImpl) {
	// Endpoint.publish(MagicStrings.EVIDENCESERVICEENDPOINT,
	// evidenceServiceImpl);
	// System.out.println("publishing wsdl to " +
	// MagicStrings.EVIDENCESERVICEENDPOINT);
	// }

}
