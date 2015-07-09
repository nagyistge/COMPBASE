package uzuzjmd.competence.main;

import java.io.IOException;

import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.CompetenceServiceRestJSON;
import uzuzjmd.competence.service.rest.CompetenceServiceRestXML;
import uzuzjmd.competence.service.rest.ResponseCorsFilter;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class RestServer {

	public static void main(String[] args) throws IllegalArgumentException, NullPointerException, IOException {

		if (args.length > 0) {
			MagicStrings.TDBLocationPath = args[0];
		}

		if (args.length > 1) {
			MagicStrings.webapplicationPath = args[1];
		}

		startServer();
	}

	public static void startServer() throws IOException {
		System.out.println("usage is java - jar *.version.jar TDBPATH WEBAPPATH");
		System.out.println("for example java jar competence-server.jar tdb2 pojana.soft.cs.uni-potsdam.de/competence-portlet");

		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");

		MagicStrings.runsAsJar = true;

		ResourceConfig resourceConfig = new DefaultResourceConfig(CompetenceServiceRestXML.class, CompetenceServiceRestJSON.class, EvidenceServiceRestServerImpl.class);
		resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
		GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURLCompetence, resourceConfig);
		System.out.println("publishing competence server to to " + MagicStrings.RESTURLCompetence);
		System.out.println("Test this with: " + MagicStrings.RESTURLCompetence + "/competences/xml/competencetree/university/all/nocache");

		System.out.println("Press enter to exit");
		System.in.read();
	}

}
