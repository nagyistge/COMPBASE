package uzuzjmd.competence.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import uzuzjmd.competence.logging.LogConfigurator;

@ApplicationPath("/")
public class MyRESTAPIApp extends ResourceConfig {
	public MyRESTAPIApp() {
		LogConfigurator.init();
		packages("uzuzjmd.competence");
		register(org.glassfish.jersey.filter.LoggingFilter.class);
		property(
				"jersey.config.beanValidation.enableOutputValidationErrorEntity.server",
				"true");
	}
}