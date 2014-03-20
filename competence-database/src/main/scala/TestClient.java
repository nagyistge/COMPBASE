import java.rmi.RemoteException;

import org.imsglobal.www.xsd.imsrdceo_rootv1p0.Description;
import org.imsglobal.www.xsd.imsrdceo_rootv1p0.Title;

import uzuzjmd.owl.util.MagicStrings;
import uzuzjmd.service.competence.client.CompetenceService;
import uzuzjmd.service.competence.client.CompetenceServiceProxy;
import uzuzjmd.service.competence.client.InsertCompetenceArg0;


public class TestClient {

	public static void main(String[] args) throws RemoteException {
		CompetenceServiceProxy competenceServiceProxy = new CompetenceServiceProxy(MagicStrings.SERVICEENDPOINT);
		CompetenceService service = competenceServiceProxy.getCompetenceService();
		service.getCompetences();
		Title title = new Title();
		title.setLangstring(new String[]{"hello"});
		InsertCompetenceArg0 competence = new InsertCompetenceArg0("uri",title,new Description(), null,null,null );
		service.insertCompetence(competence);
	}

}
