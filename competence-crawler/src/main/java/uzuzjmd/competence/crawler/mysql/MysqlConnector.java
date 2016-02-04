package uzuzjmd.competence.crawler.mysql;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.specs2.internal.scalaz.Alpha;
import uzuzjmd.competence.comparison.synonyms.OpenThesaurusSynonymCreator;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.crawler.exception.NoResultsException;
import uzuzjmd.database.mysql.MysqlConnect;
import uzuzjmd.database.mysql.VereinfachtesResultSet;

import java.util.List;

/**
 * Created by carl on 03.02.16.
 */
public class MysqlConnector {
    String connectionString = "jdbc:mysql://" + MagicStrings.thesaurusDatabaseUrl +
      "/Hochschulen" +
      "?user=" + MagicStrings.thesaurusLogin +
      "&password=" + MagicStrings.thesaurusPassword;

    static private final Logger logger = LogManager.getLogger(MysqlConnector.class.getName());

    public VereinfachtesResultSet queryDomain(String domain) throws NoResultsException {
        logger.debug("Entering queryDomain with domain:" + domain);
        MysqlConnect connector = new MysqlConnect();
        connector.connect(connectionString);
        String query = "Select * from Hochschulen where Homepage like ?";
        VereinfachtesResultSet result = connector.issueSelectStatement(query, "%" + domain + "%");
        if ((result == null) || (! result.isBeforeFirst()) ) {
            logger.debug("Leaving queryDomain with 0 fetches");
            throw new NoResultsException("No Results where fetched");
        }
        logger.debug("Leaving queryDomain with >0 fetches");
        return result;
    }
}
