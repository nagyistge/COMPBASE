package uzuzjmd.competence.owl.access;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
}
