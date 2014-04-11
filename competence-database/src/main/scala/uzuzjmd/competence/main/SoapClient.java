package uzuzjmd.competence.main;

import java.rmi.RemoteException;

import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.soap.client.CompetenceService;
import uzuzjmd.competence.service.soap.client.CompetenceServiceProxy;

public class SoapClient {

	public static void main(String[] args) throws RemoteException {
		CompetenceServiceProxy competenceServiceProxy = new CompetenceServiceProxy(
				MagicStrings.SERVICEENDPOINT);
		CompetenceService service = competenceServiceProxy
				.getCompetenceService();
		service.getCompetences();
		// Title title = new Title();
		// title.setLangstring(new String[] { "hello" });
		// InsertCompetenceArg0 competence = new InsertCompetenceArg0("uri",
		// title, new Description(), null, null, null);
		// service.insertCompetence(competence);
	}

}
