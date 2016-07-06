package uzuzjmd.competence.config;

import javax.ws.rs.ApplicationPath;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.jersey.listing.ApiListingResourceJSON;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.service.rest.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyRESTAPIApp extends ResourceConfig {

	private Logger logger = org.apache.log4j.LogManager
			.getLogger(MyRESTAPIApp.class);

	public MyRESTAPIApp() {
		// LogConfigurator.init();
		packages("uzuzjmd.competence");
		register(org.glassfish.jersey.filter.LoggingFilter.class);
		property(
				"jersey.config.beanValidation.enableOutputValidationErrorEntity.server",
				"true");

		logger.info("Initiated Logger");
		logger.info("Initiated Server");
		DBInitializer.init();

/*		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1");
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/competence-base/api1");
		beanConfig.setResourcePackage("io.swagger.resources");
		beanConfig.setScan(true);
		register(beanConfig);
		register(new ApiListingResourceJSON());
		register(new SwaggerSerializers());*/

	}

/*	@Override
	public Set<Class<?>> getClasses() {
		// set your resources here
		HashSet result = new HashSet<Class>();
		result.addAll(Arrays.asList(new Class[] {LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class, ActivityApiImpl.class, RecommenderApiImpl.class}));
		return result;
	}*/
}