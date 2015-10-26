package uzuzjmd.competence.config;

import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

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
	}
}