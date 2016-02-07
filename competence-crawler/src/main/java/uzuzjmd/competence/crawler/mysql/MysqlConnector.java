package uzuzjmd.competence.crawler.mysql;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.crawler.exception.NoResultsException;
import uzuzjmd.database.mysql.MysqlConnect;
import uzuzjmd.database.mysql.VereinfachtesResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carl on 03.02.16.
 */
public class MysqlConnector {
    String connectionString = "jdbc:mysql://" + MagicStrings.thesaurusDatabaseUrl +
      "/Hochschulen" +
      "?user=" + MagicStrings.thesaurusLogin +
      "&password=" + MagicStrings.thesaurusPassword;
    private VereinfachtesResultSet hochschulen;
    private List<MysqlResult> mysqlResults;

    static private final Logger logger = LogManager.getLogger(MysqlConnector.class.getName());

    public MysqlConnector() {
        MysqlConnect connector = new MysqlConnect();
        connector.connect(connectionString);
        hochschulen = connector.issueSelectStatement("Select * from Hochschulen");
        mysqlResults = new ArrayList<>();
        while (hochschulen.next() ) {
            MysqlResult msr = new MysqlResult();
            msr.bundesland = hochschulen.getString("Bundesland");
            msr.hochschulname = hochschulen.getString("Hochschulname");
            msr.hochschultyp = hochschulen.getString("Hochschultyp");
            msr.homepage = hochschulen.getString("Homepage");
            msr.lat = hochschulen.getDouble("lat");
            msr.lon = hochschulen.getDouble("lon");
            mysqlResults.add(msr);
        }
    }

    public MysqlResult searchDomain(String domain) throws NoResultsException {
        logger.debug("Entering searchDomain with domain:" + domain);
        for (MysqlResult msr :
                mysqlResults) {
            if (msr.homepage.contains(domain)) {
                return msr;
            }
        }
        logger.debug("Leaving queryDomain with 0 fetches");
        throw new NoResultsException("No Domain found");
    }
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
