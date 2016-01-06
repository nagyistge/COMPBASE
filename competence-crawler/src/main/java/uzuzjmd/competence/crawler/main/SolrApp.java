package uzuzjmd.competence.crawler.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import uzuzjmd.competence.crawler.Datatype.Model;
import uzuzjmd.competence.crawler.IO.ReadCsv;

import java.io.IOException;

/**
 * Created by carl on 06.01.16.
 */
public class SolrApp {
    static private final Logger logger = LogManager.getLogger(SolrApp.class.getName());
    public static void main(String[] args) throws IOException {
        DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");
        logger.debug("Entering main");
        logger.info("Read out csv");
        ReadCsv csv = new ReadCsv("/development/scala_workspace/Wissensmodellierung/competence-crawler/data.csv");
        Model model = csv.convertToModel();
        logger.info("New Model instance. Length - StichwortVar:" + model.stichwortVarSize() + " VarMeta:"
            + model.varMetaSize());
        logger.info("Create Query");
        logger.info("Get Score from Crawling");
        logger.info("Transform Scoring into results");
        logger.debug("Leaving main");
    }
}
