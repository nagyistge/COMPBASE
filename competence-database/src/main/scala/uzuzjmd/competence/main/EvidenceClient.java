package uzuzjmd.competence.main;

import java.rmi.RemoteException;

import org.apache.commons.lang.NotImplementedException;

public class EvidenceClient {

	public static void main(String[] args) throws RemoteException {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");

		throw new NotImplementedException();

		// EvidenceServiceProxy evidenceServiceProxy = new EvidenceServiceProxy(
		// "http://localhost:8082/WS/Competence/Evidence?wsdl");
		// EvidenceService service = evidenceServiceProxy.getEvidenceService();
		// MoodleEvidence[] evidences = service.getMoodleEvidences("2", "2");
		// System.out.println("fetched evidences");

		// MoodleEvidence[] evidences2 =
		// service.getUserEvidencesforMoodleCourse("2");
		// System.out.println("fetched evidences for whole course");
	}
}
