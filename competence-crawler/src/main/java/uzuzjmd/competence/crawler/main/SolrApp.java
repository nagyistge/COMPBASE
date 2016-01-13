package uzuzjmd.competence.crawler.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import uzuzjmd.competence.crawler.datatype.Model;
import uzuzjmd.competence.crawler.io.ReadCsv;
import uzuzjmd.competence.crawler.neo4j.Neo4JConnector;
import uzuzjmd.competence.crawler.solr.SolrConnector;

import java.io.IOException;

/**
 * Created by carl on 06.01.16.
 */
public class SolrApp {
    static private final Logger logger = LogManager.getLogger(SolrApp.class.getName());
    static private final String solrUrl = "http://localhost:8983/solr/basic";
    static private final String stichWortPath = "/development/scala_workspace/Wissensmodellierung/"
                + "competence-crawler/stichwortUrl.csv";
    static private final String stichWortVarPath = "/development/scala_workspace/Wissensmodellierung/"
            + "competence-crawler/stichwortVar.csv";
    static private final String varMetaPath = "/development/scala_workspace/Wissensmodellierung/"
            + "competence-crawler/varMeta.csv";
    static private final String dataPath =
            "/development/scala_workspace/Wissensmodellierung/competence-crawler/data.csv";
    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");
        logger.debug("Entering main");

        logger.info("Read out csv");
        ReadCsv csv = new ReadCsv(dataPath);
        Neo4JConnector nj = new Neo4JConnector();
        SolrConnector connector = new SolrConnector(solrUrl);
        Model model = csv.convertToModel();
        logger.info("New Model instance. Length - StichwortVar:" + model.stichwortVarSize() + " VarMeta:"
                + model.varMetaSize());
        model.deleteModelInNeo4J();
        nj.queryMyStatements(model.toNeo4JQuery());
        logger.info("The model has been put into Neo4J");
        logger.info("Create Query");
        model.scoreStichwort(connector);
        model.scoreVariable(connector);
        logger.info("Get Score from Crawling");
        logger.info("Transform Scoring into results");
        model.stichwortVarToCsv(stichWortVarPath);
        model.stichwortResultToCsv(stichWortPath);
        model.varMetaResultToCsv(varMetaPath);
        logger.debug("Leaving main");
    }
}
