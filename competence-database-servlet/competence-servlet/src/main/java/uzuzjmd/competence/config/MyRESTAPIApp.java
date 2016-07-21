package uzuzjmd.competence.config;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import uzuzjmd.competence.persistence.dao.DBInitializer;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MyRESTAPIApp extends ResourceConfig {

	private Logger logger = org.apache.log4j.LogManager
			.getLogger(MyRESTAPIApp.class);

	public MyRESTAPIApp() {

		packages("uzuzjmd.competence");
		register(org.glassfish.jersey.filter.LoggingFilter.class);
		property(
				"jersey.config.beanValidation.enableOutputValidationErrorEntity.server",
				"true");

		logger.info("Initiated Logger");
		logger.info("Initiated Server");
		DBInitializer.init();

		/*logger.info("initiating swagger config");
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		packages("io.swagger.jaxrs.listing");

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setHost("http://localhost:8080");
		beanConfig.setBasePath("/competence-base");
		beanConfig.setScan(true);
		beanConfig.setResourcePackage("uzuzjmd.competence");
		registerInstances(beanConfig);*/


	}

/*	@Override
	public Set<Class<?>> getClasses() {
		// set your resources here
		HashSet result = new HashSet<Class>();
		result.addAll(Arrays.asList(new Class[] {LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class, ActivityApiImpl.class, RecommenderApiImpl.class}));
		return result;
	}*/
}