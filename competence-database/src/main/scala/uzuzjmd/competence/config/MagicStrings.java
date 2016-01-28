package uzuzjmd.competence.config;

/**
 * contains the main configuration for the project which are defined in
 * evidenceserver.properties
 */
public class MagicStrings {
	public static final String ROOTPATH = PropUtil
			.getProp("rootPath");

	public static final String MOODLEURL = PropUtil
			.getProp("moodleURL");
	public static final String webapplicationPath = PropUtil
			.getProp("webapplicationPath");
	public static final String RESTURLCompetence = "http://localhost:8084";
	public static final String ICONPATHMOODLE = "icons/WindowsIcons-master/WindowsPhone/svg";
	public static final String EPOSLocation = PropUtil
			.getRelativeFileProp("eposfile");
	public static final String GERMANMODELLOCATION = "germanPCFG.ser.gz";
	public static final String thesaurusLogin = PropUtil
			.getProp("thesaurusLogin");
	public static final String thesaurusPassword = PropUtil
			.getProp("thesaurusPassword");
	public static final String thesaurusDatabaseName = PropUtil
			.getProp("thesaurusDatabaseName");
	public static final String thesaurusDatabaseUrl = PropUtil
			.getProp("thesaurusDatabaseUrl");
	public static final String NEO4JURL = PropUtil
			.getProp("neo4jURL");

}
