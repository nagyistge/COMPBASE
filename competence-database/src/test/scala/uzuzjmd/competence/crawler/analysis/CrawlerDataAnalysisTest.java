package uzuzjmd.competence.crawler.analysis;

import datastructures.Pair;
import mysql.VereinfachtesResultSet;
import org.junit.Test;

import scala.Predef;
import scala.collection.immutable.List;
import uzuzjmd.competence.crawler.mysql.MysqlConnector;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertFalse;

/**
 * Created by dehne on 07.04.2016.
 */
public class CrawlerDataAnalysisTest {

    @Test
    public void testSelectRelevantDataForPlotting() throws Exception {
        String databaseName = "unidisk";
        String tableName = "testcrawl_varmeta";

        LinkedList<Pair<Double>> latLonsTaken = new LinkedList<Pair<Double>>();
        MysqlConnector mysqlConnect = new MysqlConnector(databaseName);
        VereinfachtesResultSet result = mysqlConnect.connector.issueSelectStatement("Select `Hochschule`, `SolrScore`, `Lat`, `Lon` from `"+tableName+"`");
        HashMap<Double, String> inputData = new HashMap<>();
        HashMap<Pair<Double>, Double> latLongSolrMap = new HashMap<>();
        while(result.next()) {
             Pair<Double> latLonPair = new Pair<Double>(result.getDouble("Lat"), result.getDouble("Lon"));
             Double solrScore = result.getDouble("SolrScore");
             String hochschule = result.getString("Hochschule");
             if (latLonsTaken.contains(latLonPair)) {
                 Double oldValue = latLongSolrMap.get(latLonPair);
                 solrScore = oldValue + solrScore;
                 inputData.remove(oldValue);
             }
             latLonsTaken.add(latLonPair);
             inputData.put(solrScore, hochschule);
             latLongSolrMap.put(latLonPair, solrScore);
        }
        assertFalse(inputData.isEmpty());
        Collection<String> result2 = CrawlerDataAnalysis.selectRelevantDataForPlotting(inputData);
        assertFalse(result2.isEmpty());
    }
}