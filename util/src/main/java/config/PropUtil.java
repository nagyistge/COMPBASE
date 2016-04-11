package config;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropUtil {

	public static HashMap<String,String> defaults;

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
				//To know where the file is searched, uncomment the text below
				System.out.println(Paths.get(".").toAbsolutePath().toString());
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


}
