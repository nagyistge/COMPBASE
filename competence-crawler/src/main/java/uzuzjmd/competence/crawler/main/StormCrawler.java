package uzuzjmd.competence.crawler.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.digitalpebble.storm.crawler.ConfigurableTopology;

public class StormCrawler extends ConfigurableTopology {

	static Logger logger = LogManager.getLogger(StormCrawler.class.getName());
	@Override
	protected int run(String[] arg0) {
		String myArgs = "";
		for (String arg : arg0) {
			myArgs += arg + ", ";
		}
		logger.debug("Entering run with " + myArgs);
		// TODO Auto-generated method stub
		logger.debug("Leaving run");
		return 0;
	}
	
	public static void main(String[] args) {
		DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");

		String myArgs = "";
		for (String arg : args) {
			myArgs += arg;
		}
		logger.debug("Entering main with " + myArgs);
        ConfigurableTopology.start(new StormCrawler(), args);

		logger.debug("Leaving main");
	}

}
