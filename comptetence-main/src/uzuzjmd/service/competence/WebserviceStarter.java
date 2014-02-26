package uzuzjmd.service.competence;

import javax.xml.ws.Endpoint;

import uzuzjmd.owl.util.MagicStrings;

public class WebserviceStarter {

	public static void main(String[] args) {
		Endpoint.publish(MagicStrings.SERVICEENDPOINT, new CompetenceServiceImpl());
		System.out.println("publishing wsdl to " + MagicStrings.SERVICEENDPOINT);
	}

}
