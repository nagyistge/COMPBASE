package uzuzjmd.competence.crawler.main;

import config.MagicStrings;
import neo4j.Neo4JConnector;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import uzuzjmd.competence.crawler.datatype.Model;
import uzuzjmd.competence.crawler.io.ReadCsv;
import uzuzjmd.competence.crawler.io.ReadMysql;
import uzuzjmd.competence.crawler.solr.SolrConnector;

/**
 * Created by carl on 06.01.16.
 */
public class SolrApp {
    static private final Logger logger = LogManager.getLogger(SolrApp.class.getName());
    static private final String solrUrl = "http://learnlib.soft.cs.uni-potsdam.de:80/solr/test2";
    static private final String database = "UnitTest";

    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure(MagicStrings.LOG4JLOCATION);
        System.out.print("starting csv creation");
        logger.debug(MagicStrings.ROOTPATH);
        logger.debug("Entering main");
        logger.info("Read out csv or mysql");

        //ReadCsv csv = new ReadCsv(MagicStrings.dataPath);
        ReadMysql mysql = new ReadMysql();
        Neo4JConnector nj = new Neo4JConnector();
        SolrConnector connector = new SolrConnector(solrUrl);
        Model model = mysql.convertToModel(database);
        logger.info("New Model instance. Length - StichwortVar:" + model.stichwortVarSize() + " VarMeta:"
                + model.varMetaSize());
        //model.insertSynonyms();
        logger.info("Model instance with Synonyms. Length - StichwortVar:" + model.stichwortVarSize() + " VarMeta:"
                + model.varMetaSize());
        nj.queryMyStatements(model.toNeo4JQuery());
        logger.info("The model has been put into Neo4J");
        logger.info("Create Query");
        //model.scoreStichwort(connector);
        model.initStichFile(MagicStrings.stichWortPath);
        model.initVarMetaFile(MagicStrings.varMetaPath);
        model.scoreStichwort(connector, MagicStrings.stichWortPath);
        //model.scoreVariable(connector);
        model.scoreVariable(connector, MagicStrings.varMetaPath);
        logger.info("Get Score from Crawling");
        logger.info("Transform Scoring into results");
        //model.stichwortVarToCsv(MagicStrings.stichWortVarPath);
        //model.stichwortResultToCsv(MagicStrings.stichWortPath);
        //model.varMetaResultToCsv(MagicStrings.varMetaPath);
        logger.debug("Leaving main");
        System.out.print("finishing csv creation");
    }
}
