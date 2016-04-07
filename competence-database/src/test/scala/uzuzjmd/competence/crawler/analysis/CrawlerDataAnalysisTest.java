package uzuzjmd.competence.crawler.analysis;

import mysql.VereinfachtesResultSet;
import org.junit.Test;
import scala.collection.immutable.List;
import uzuzjmd.competence.crawler.mysql.MysqlConnector;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;

/**
 * Created by dehne on 07.04.2016.
 */
public class CrawlerDataAnalysisTest {

    @Test
    public void testSelectRelevantDataForPlotting() throws Exception {
        MysqlConnector mysqlConnect = new MysqlConnector("unidisk");
        VereinfachtesResultSet result = mysqlConnect.connector.issueSelectStatement("Select `Hochschule`, `SolrScore` from `testcrawl_varmeta`");
        HashMap<Double, String> inputData = new HashMap<>();
        while(result.next()) {
             inputData.put(result.getDouble("SolrScore"), result.getString("Hochschule"));
        }
        assertFalse(inputData.isEmpty());

        Collection<String> result2 = CrawlerDataAnalysis.selectRelevantDataForPlotting(inputData);
        assertFalse(result2.isEmpty());

    }
}