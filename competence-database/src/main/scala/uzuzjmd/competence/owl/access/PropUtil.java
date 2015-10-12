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
	public static Properties getProperties() {
		Properties prop = new Properties();
		String propfFileName = "evidenceserver.properties";

		// find file
		InputStream inputStream;
		try {
			if (MagicStrings.runsAsJar) {
				inputStream = new FileInputStream(propfFileName);
			} else {
				inputStream = PropUtil.class.getClassLoader().getResourceAsStream(propfFileName);
			}
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	public static String getProp(String key) {
		return getProperties().getProperty(key).replaceAll("\"", "");
	}

	/**
	 * adds the rootPath as prefix
	 * 
	 * @param key
	 * @return
	 */
	public static String getRelativeFileProp(String key) {
		return MagicStrings.ROOTPATH + getProperties().getProperty(key).replaceAll("\"", "");
	}

	/**
	 * adds the rootPath as prefix
	 * 
	 * @param key
	 * @return
	 */
	public static String getRelativeOrAbsoluteFileProp(String relativeKey, String absoluteKey) {
		if (getProperties().getProperty(relativeKey) != null) {
			return MagicStrings.ROOTPATH + getProperties().getProperty(relativeKey).replaceAll("\"", "");
		} else {
			return getProperties().getProperty(absoluteKey).replaceAll("\"", "");
		}
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
