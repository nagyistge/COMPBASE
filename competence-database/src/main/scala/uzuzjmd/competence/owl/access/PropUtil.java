package uzuzjmd.competence.owl.access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class PropUtil {
	public Properties getProperties() {
		Properties prop = new Properties();
		String propfFileName = "evidenceserver.properties";

		// find file
		InputStream inputStream;
		try {
			if (MagicStrings.runsAsJar) {
				inputStream = new FileInputStream(propfFileName);
			} else {
				inputStream = this.getClass().getClassLoader().getResourceAsStream(propfFileName);
			}
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	public void doStandard() {
		MagicStrings.webapplicationPath = getProperties().getProperty("webapplicationPath");
		MagicStrings.TDBLocationPath = getProperties().getProperty("tdblocation");
		// do ThesaurusLogin
		MagicStrings.thesaurusLogin = getProperties().getProperty("thesaurusLogin");
		MagicStrings.thesaurusPassword = getProperties().getProperty("thesaurusPassword");
		MagicStrings.thesaurusDatabaseName = getProperties().getProperty("thesaurusDatabaseName");
		MagicStrings.thesaurusDatabaseUrl = getProperties().getProperty("thesaurusDatabaseUrl");
	}

	public void configureLogger() {
		ConsoleAppender console = new ConsoleAppender(); // create appender
		// configure the appender
		String PATTERN = "%d [%p|%c|%C{1}] %m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.WARN);
		console.activateOptions();
		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(console);

		FileAppender fa = new FileAppender();
		fa.setName("FileLogger");
		fa.setFile("mylog.log");
		fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		fa.setThreshold(Level.WARN);
		fa.setAppend(true);
		fa.activateOptions();

		// add appender to any Logger (here is root)
		Logger.getRootLogger().addAppender(fa);
		// repeat with all other desired appenders

	}
}
