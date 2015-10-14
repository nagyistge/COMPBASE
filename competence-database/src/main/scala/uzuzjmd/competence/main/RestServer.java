package uzuzjmd.competence.main;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.ProcessingException;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.CompetenceServiceRestJSON;
import uzuzjmd.competence.service.rest.CompetenceServiceRestXML;

public class RestServer {

	public static void main(String[] args) throws IllegalArgumentException, NullPointerException, IOException, ProcessingException, URISyntaxException {
		startServer();
	}

	public static void startServer() throws IOException, ProcessingException, URISyntaxException {
		System.out.println("usage is java - jar *.version.jar TDBPATH WEBAPPATH");
		System.out.println("for example java jar competence-server.jar tdb2 pojana.soft.cs.uni-potsdam.de/competence-portlet");

		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");

		ResourceConfig resourceConfig = new ResourceConfig(CompetenceServiceRestXML.class, CompetenceServiceRestJSON.class, EvidenceServiceRestServerImpl.class);
		resourceConfig.register(JacksonFeature.class);
		// final Map<String, Object> initParams = new HashMap<String, Object>();
		// // initParams.put("com.sun.jersey.config.property.packages", "rest");
		// initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		//
		// resourceConfig.addProperties(initParams);

		// ResourceConfig resourceConfig = new
		// ResourceConfig(CompetenceServiceRestXML.class,
		// CompetenceServiceRestJSON.class,
		// EvidenceServiceRestServerImpl.class);
		// resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
		// GrizzlyHttpServerFactory.createHttpServer(new
		// URI(MagicStrings.RESTURLCompetence), resourceConfig);

		GrizzlyHttpServerFactory.createHttpServer(new URI(MagicStrings.RESTURLCompetence), resourceConfig);

		System.out.println("publishing competence server to to " + MagicStrings.RESTURLCompetence);
		System.out.println("Test this with: " + MagicStrings.RESTURLCompetence + "/competences/xml/competencetree/university/all/nocache");

		System.out.println("Press enter to exit");
		System.in.read();
	}

}
