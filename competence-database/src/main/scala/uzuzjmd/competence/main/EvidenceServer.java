package uzuzjmd.competence.main;

import javax.xml.ws.Endpoint;

import uzuzjmd.competence.evidence.service.EvidenceServiceImpl;
import uzuzjmd.competence.owl.access.MagicStrings;

public class EvidenceServer {

	public static void main(String[] args) {
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
				new EvidenceServiceImpl(moodleurl, moodledb, adminname,
						adminpassword));
		System.out.println("publishing wsdl to "
				+ MagicStrings.EVIDENCESERVICEENDPOINT);
	}

}
