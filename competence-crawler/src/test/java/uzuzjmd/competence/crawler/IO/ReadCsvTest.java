package uzuzjmd.competence.crawler.IO;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.xml.DOMConfigurator;
import uzuzjmd.competence.crawler.Datatype.Model;

/**
 * Created by carl on 06.01.16.
 */
public class ReadCsvTest extends TestCase {

    public ReadCsvTest() {
        super("Test Model Converter");
        DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-crawler/log4j.xml");
    }

    public static Test suite() {
        return new TestSuite(ReadCsvTest.class);
    }
    public void testConvertToModel() throws Exception {
        ReadCsv csv = new ReadCsv("/development/scala_workspace/Wissensmodellierung/competence-crawler/testdata.csv");
        Model model = csv.convertToModel();

        assertEquals(4, model.stichwortVarSize());
        assertEquals(6, model.varMetaSize());
    }
}