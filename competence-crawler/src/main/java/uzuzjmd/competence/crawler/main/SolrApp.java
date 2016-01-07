package uzuzjmd.competence.crawler.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import uzuzjmd.competence.crawler.datatype.Model;
import uzuzjmd.competence.crawler.io.ReadCsv;
import uzuzjmd.competence.crawler.neo4j.Neo4JConnector;

import java.io.IOException;

/**
 * Created by carl on 06.01.16.
 */
public class SolrApp {
    static private final Logger logger = LogManager.getLogger(SolrApp.class.getName());
    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");
        logger.debug("Entering main");

        logger.info("Read out csv");
        ReadCsv csv = new ReadCsv("/development/scala_workspace/Wissensmodellierung/competence-crawler/data.csv");
        Neo4JConnector nj = new Neo4JConnector();
        Model model = csv.convertToModel();
        logger.info("New Model instance. Length - StichwortVar:" + model.stichwortVarSize() + " VarMeta:"
                + model.varMetaSize());
        model.deleteModelInNeo4J();
        nj.queryMyStatements(model.toNeo4JQuery());
        logger.info("The model has been put into Neo4J");
        logger.info("Create Query");
        logger.info("Get Score from Crawling");
        logger.info("Transform Scoring into results");
        logger.debug("Leaving main");
    }
}
