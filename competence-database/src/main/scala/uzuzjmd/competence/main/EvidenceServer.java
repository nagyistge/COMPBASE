package uzuzjmd.competence.main;

import javax.xml.ws.Endpoint;

import uzuzjmd.competence.evidence.service.MoodleEvidenceServiceImpl;
import uzuzjmd.competence.owl.access.MagicStrings;

public class EvidenceServer {

	public static void main(String[] args) {

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
			String adminname, String adminpassword) {
		Endpoint.publish(MagicStrings.EVIDENCESERVICEENDPOINT,
				new MoodleEvidenceServiceImpl(moodleurl, moodledb, adminname,
						adminpassword));
		System.out.println("publishing wsdl to "
				+ MagicStrings.EVIDENCESERVICEENDPOINT);
	}

}