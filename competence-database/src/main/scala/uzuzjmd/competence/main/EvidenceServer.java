package uzuzjmd.competence.main;

import java.io.IOException;

import uzuzjmd.competence.evidence.service.MoodleEvidenceServiceImpl;
import uzuzjmd.competence.evidence.service.MoodleEvidenceServiceRestImpl;
import uzuzjmd.competence.owl.access.MagicStrings;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;

public class EvidenceServer {

	public static void main(String[] args) throws IllegalArgumentException,
			NullPointerException, IOException {

		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory",
				"org.apache.commons.logging.impl.LogFactoryImpl");

		if (args.length != 4) {
			System.out
					.println("Die Verwendung lautet java -jar EvidenceServerJar moodleurl moodledb adminname adminpassword");
		} else {
			String moodleurl = args[0];
			String moodledb = args[1];
			String adminname = args[2];
			String adminpassword = args[3];
			startServer(moodleurl, moodledb, adminname, adminpassword);
		}

	}

	private static void startServer(String moodleurl, String moodledb,
			String adminname, String adminpassword)
			throws IllegalArgumentException, NullPointerException, IOException {
		final MoodleEvidenceServiceImpl evidenceServiceImpl = new MoodleEvidenceServiceImpl(
				moodleurl, moodledb, adminname, adminpassword);
		MoodleEvidenceServiceRestImpl.moodleServiceImpl = evidenceServiceImpl;

		// Endpoint.publish(MagicStrings.EVIDENCESERVICEENDPOINT,
		// evidenceServiceImpl);
		// System.out.println("publishing wsdl to "
		// + MagicStrings.EVIDENCESERVICEENDPOINT);

		// Map<String, Object> initParams = new HashMap<String, Object>();
		// initParams.put("com.sun.jersey.config.property.packages",
		// "uzuzjmd.competence.evidence.service");
		// initParams.put("com.sun.jersey.config.property.packages",
		// "uzuzjmd.competence.evidence.model");
		// ResourceConfig config = new DefaultResourceConfig(
		// MoodleEvidenceServiceImpl.class, MoodleEvidence.class,
		// Evidence.class);
		// config.getProperties().putAll(initParams);
		// // config.setPropertiesAndFeatures(initParams);
		// GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURL, config);
		// GrizzlyServerFactory.createHttpServer();

		GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURL);
		System.out.println("publishing rest server to to "
				+ MagicStrings.RESTURL);

		System.out.println("Press enter to exit");
		System.in.read();

		System.exit(1);

	}

}
