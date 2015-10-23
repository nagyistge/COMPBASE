package uzuzjmd.competence.owl.access;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropUtil {

	private static Logger logger = LogManager
			.getLogger(PropUtil.class);

	public static boolean _amServer() {
		StackTraceElement[] elements = new Throwable()
				.getStackTrace();

		for (StackTraceElement element : elements) {
			if (element
					.getClassName()
					.equals("org.apache.catalina.core.StandardEngineValve")) {
				return true;
			}
		}

		return false;
	}

	public static Properties getProperties() {
		Properties prop = new Properties();
		String propfFileName = "evidenceserver.properties";

		InputStream inputStream = null;

		if (_amServer()) {
			// find file
			inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(propfFileName);
		} else {
			try {
				inputStream = new FileInputStream(
						propfFileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	public static String getProp(String key) {
		try {
			return getProperties().getProperty(key)
					.replaceAll("\"", "");
		} catch (NullPointerException p) {
			logger.error("property " + key
					+ " is not set!!!");
		}
		return null;
	}

	/**
	 * adds the rootPath as prefix
	 * 
	 * @param key
	 * @return
	 */
	public static String getRelativeFileProp(String key) {
		return MagicStrings.ROOTPATH
				+ getProperties().getProperty(key)
						.replaceAll("\"", "");
	}

	/**
	 * adds the rootPath as prefix
	 * 
	 * @param key
	 * @return
	 */
	public static String getRelativeOrAbsoluteFileProp(
			String relativeKey, String absoluteKey) {
		if (getProperties().getProperty(relativeKey) != null) {
			return MagicStrings.ROOTPATH
					+ getProperties().getProperty(
							relativeKey).replaceAll("\"",
							"");
		} else {
			return getProperties().getProperty(absoluteKey)
					.replaceAll("\"", "");
		}
	}

	@Deprecated
	public void configureLogger() {
		/*
		 * ConsoleAppender console = new ConsoleAppender(); // create appender
		 * // configure the appender String PATTERN = "%d [%p|%c|%C{1}] %m%n";
		 * console.setLayout(new PatternLayout(PATTERN));
		 * console.setThreshold(Level.WARN); console.activateOptions(); // add
		 * appender to any Logger (here is root)
		 * Logger.getRootLogger().addAppender(console);
		 * 
		 * 
		 * FileAppender fa = new FileAppender(); fa.setName("FileLogger");
		 * fa.setFile("mylog.log"); fa.setLayout(new
		 * PatternLayout("%d %-5p [%c{1}] %m%n")); fa.setThreshold(Level.WARN);
		 * fa.setAppend(true); fa.activateOptions();
		 * 
		 * 
		 * // add appender to any Logger (here is root)
		 * Logger.getRootLogger().addAppender(fa); // repeat with all other
		 * desired appenders
		 */

	}
}
