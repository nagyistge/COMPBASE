package uzuzjmd.competence.crawler.io;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import uzuzjmd.competence.crawler.datatype.Model;
import uzuzjmd.competence.crawler.neo4j.Neo4JConnector;

import java.io.IOException;

/**
 * Created by carl on 06.01.16.
 */
public class ReadCsvTest extends TestCase {

    ReadCsv csv;
    Model model;
    Neo4JConnector nj;

    @Before
    public void testSetUp() throws Exception {
        nj.queryMyStatements(model.deleteModelInNeo4J());
    }

    public ReadCsvTest() {
        super("Test Model Converter");
        DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");
        csv = new ReadCsv("/development/scala_workspace/Wissensmodellierung/competence-crawler/testdata.csv");
        try {
            model = csv.convertToModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nj = new Neo4JConnector();
    }

    public static Test suite() {
        return new TestSuite(ReadCsvTest.class);
    }
    public void testConvertToModel() throws Exception {

        assertEquals(4, model.stichwortVarSize());
        assertEquals(6, model.varMetaSize());
        nj.queryMyStatements(model.toNeo4JQuery());

    }
}