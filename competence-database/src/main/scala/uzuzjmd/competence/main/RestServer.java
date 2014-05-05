package uzuzjmd.competence.main;

import java.io.IOException;

import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.CompetenceServiceRestJSON;
import uzuzjmd.competence.service.rest.CompetenceServiceRestXML;
import uzuzjmd.competence.service.rest.ResponseCorsFilter;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class RestServer {

	public static void main(String[] args) throws IllegalArgumentException,
			NullPointerException, IOException {

		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.Log4JLogger");
		System.setProperty("org.apache.commons.logging.LogFactory",
				"org.apache.commons.logging.impl.LogFactoryImpl");

		ResourceConfig resourceConfig = new DefaultResourceConfig(
				CompetenceServiceRestXML.class, CompetenceServiceRestJSON.class);
		resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
		GrizzlyServerFactory.createHttpServer(MagicStrings.RESTURLCompetence,
				resourceConfig);
		System.out.println("publishing competence server to to "
				+ MagicStrings.RESTURLCompetence);
		System.out.println("Test this with: " + MagicStrings.RESTURLCompetence
				+ "/competences/xml/competencetree/{course}/all/nocache");

		System.out.println("Press enter to exit");
		System.in.read();
	}

}
