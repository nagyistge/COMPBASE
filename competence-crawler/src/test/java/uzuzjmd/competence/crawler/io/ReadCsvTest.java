package uzuzjmd.competence.crawler.io;

import junit.framework.TestCase;

/**
 * Created by carl on 06.01.16.
 */
public class ReadCsvTest extends TestCase {

  /*  ReadCsv csv;
    Model model;
    Neo4JConnector nj;
    SolrConnector conn;

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
        conn = new SolrConnector("http://localhost:8983/solr/basic");
    }

    public static Test suite() {
        return new TestSuite(ReadCsvTest.class);
    }
    public void testConvertToModel() throws Exception {
        nj.queryMyStatements(model.toNeo4JQuery());
        assertEquals(4, model.stichwortVarSize());
        assertEquals(3, model.varMetaSize());
    }

    public void testSolrConnector() throws Exception {
        QueryResponse response = conn.connectToSolr( "forschendes");
        assertTrue(response.getResults().getNumFound() > 0);
        model.scoreStichwort(conn);
        model.stichwortResultToCsv("/development/scala_workspace/Wissensmodellierung/"
                + "competence-crawler/stichwortUrl.csv");

        model.scoreVariable(conn);
        model.varMetaResultToCsv("/development/scala_workspace/Wissensmodellierung/"
                + "competence-crawler/varMeta.csv");

    }

    public void testTherasaurus() {
        java.util.List<String> synonyms = OpenThesaurusSynonymCreator.getSysnonymsAsJava("Test");

        for (String syn : synonyms) {
            System.out.println(syn);
        }

    }*/
}