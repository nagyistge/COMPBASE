package uzuzjmd.competence.crawler.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.elasticsearch.storm.EsBolt;

import backtype.storm.topology.TopologyBuilder;

import com.digitalpebble.storm.crawler.ConfigurableTopology;
import com.digitalpebble.storm.*;

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
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new RandomSentenceSpout(), 10);
        builder.setBolt("esBolt", new EsBolt("twitter/tweets"));
        //builder.setSpout("spout", new ElasticSearchSpout());

        /*
        builder.setBolt("partitioner", new URLPartitionerBolt())
                .shuffleGrouping("esBolt");

        builder.setBolt("fetch", new FetcherBolt()).fieldsGrouping(
                "partitioner", new Fields("key"));

        builder.setBolt("sitemap", new SiteMapParserBolt())
                .localOrShuffleGrouping("fetch");

        builder.setBolt("parse", new JSoupParserBolt()).localOrShuffleGrouping(
                "sitemap");

        builder.setBolt("indexer", new IndexerBolt()).localOrShuffleGrouping(
                "parse");

        builder.setBolt("status", new StatusUpdaterBolt())
                .localOrShuffleGrouping("fetch", Constants.StatusStreamName)
                .localOrShuffleGrouping("sitemap", Constants.StatusStreamName)
                .localOrShuffleGrouping("parse", Constants.StatusStreamName)
                .localOrShuffleGrouping("indexer", Constants.StatusStreamName);

        conf.registerMetricsConsumer(MetricsConsumer.class);

        int result = submit("crawl", conf, builder);
        */
        int result = 1;
        logger.debug("Leaving run with " + result);
        return result;
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
