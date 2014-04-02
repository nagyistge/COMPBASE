package uzuzjmd.competence.main;

import javax.xml.ws.Endpoint;

import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.CompetenceServiceImpl;

public class SoapServer {
	public static void main(String[] args) {
		Endpoint.publish(MagicStrings.SERVICEENDPOINT, new CompetenceServiceImpl());
		System.out.println("publishing wsdl to " + MagicStrings.SERVICEENDPOINT);
	}
}
