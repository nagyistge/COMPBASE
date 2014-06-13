package uzuzjmd.competence.main;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import uzuzjmd.competence.evidence.service.MoodleEvidenceServiceImpl;
import uzuzjmd.competence.evidence.service.rest.MoodleEvidenceServiceRestImpl;
import uzuzjmd.competence.evidence.service.rest.dto.ActivityEntry;
import uzuzjmd.competence.evidence.service.rest.dto.ActivityTyp;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.ResponseCorsFilter;
import uzuzjmd.competence.view.xml.AbstractTreeEntry;
import uzuzjmd.competence.view.xml.AbstractXMLTree;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class EvidenceServer {

	public static void main(String[] args) throws IllegalArgumentException, NullPointerException, IOException {

		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");

		if (args.length != 5 && args.length != 7) {
			System.out.println("Die Verwendung lautet java -jar EvidenceServerJar moodlename moodledb adminname adminpassword [adminlogin adminloginpassword] moodleurl");
		} else {
			String moodleurl = args[0];
			MagicStrings.MOODLEURL = args[args.length - 1];

			String moodledb = args[1];
			String adminname = args[2];
			String adminpassword = args[3];
			if (args.length == 6) {
				String adminlogin = args[4];
				String adminloginpassword = args[5];
				startServer(moodleurl, moodledb, adminname, adminpassword, adminlogin, adminloginpassword);
			} else {
				startServer(moodleurl, moodledb, adminname, adminpassword);
			}
		}

	}

	private static void startServer(String moodleurl, String moodledb, String adminname, String adminpassword, String adminlogin, String adminloginpassword) throws IOException {
		MoodleEvidenceServiceImpl evidenceServiceImpl = new MoodleEvidenceServiceImpl(moodleurl, moodledb, adminname, adminpassword, adminlogin, adminloginpassword);
		publishServer(evidenceServiceImpl);
	}

	private static void startServer(String moodleurl, String moodledb, String adminname, String adminpassword) throws IllegalArgumentException, NullPointerException, IOException {
		// start server
		final MoodleEvidenceServiceImpl evidenceServiceImpl = new MoodleEvidenceServiceImpl(moodleurl, moodledb, adminname, adminpassword);
		publishServer(evidenceServiceImpl);

	}

	private static void publishServer(final MoodleEvidenceServiceImpl evidenceServiceImpl) throws IOException {
		MoodleEvidenceServiceRestImpl.moodleServiceImpl = evidenceServiceImpl;

		publishSoapServer(evidenceServiceImpl);
		publishRestServer();

		System.out.println("Press enter to exit");
		System.in.read();

		System.exit(1);
	}

	private static void publishRestServer() throws IOException {
		ResourceConfig resourceConfig = new DefaultResourceConfig(MoodleEvidenceServiceRestImpl.class, UserTree.class, AbstractTreeEntry.class, AbstractXMLTree.class, ActivityTyp.class,
				ActivityEntry.class);
		resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
		GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURL, resourceConfig);
		System.out.println("publishing rest server to to " + MagicStrings.RESTURL);
		System.out.println("Test this with2: " + MagicStrings.RESTURL + "/moodle/activities/json/2/2");
	}

	private static void publishSoapServer(final MoodleEvidenceServiceImpl evidenceServiceImpl) {
		Endpoint.publish(MagicStrings.EVIDENCESERVICEENDPOINT, evidenceServiceImpl);
		System.out.println("publishing wsdl to " + MagicStrings.EVIDENCESERVICEENDPOINT);
	}

}
