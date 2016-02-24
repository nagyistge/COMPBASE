package uzuzjmd.competence.crawler.datatype;

import mysql.VereinfachtesResultSet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.crawler.exception.NoDomainFoundException;
import uzuzjmd.competence.crawler.exception.NoHochschuleException;
import uzuzjmd.competence.crawler.exception.NoResultsException;
import uzuzjmd.competence.crawler.mysql.MysqlConnector;
import uzuzjmd.competence.crawler.mysql.MysqlResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carl on 03.02.16.
 */
public class Urls {
    private List<UrlHochschule> urls;
    MysqlConnector mysqlConn = new MysqlConnector();
    static private final Logger logger = LogManager.getLogger(Urls.class.getName());

    public Urls() {
        urls = new ArrayList<>();
    }

    public void addDomain(String domain, String host) {
        //logger.debug("Entering addDomain with domain:" + domain + " from " + host);
        for (UrlHochschule urlh :
                urls) {
            if (urlh.domain.equals(domain)) {
                urlh.count++;
                //logger.debug("Leaving addDomain with added + 1");
                return;
            }
        }
        UrlHochschule urlh = new UrlHochschule();
        urlh.domain = domain;
        try {

            MysqlResult msr = mysqlConn.searchDomain(urlh.domain);
            urlh.hochschule = true;
            //vrs.next();
            urlh.Hochschulname = msr.hochschulname;
            urlh.lat = msr.lat;
            urlh.lon = msr.lon;
        } catch (NoResultsException e) {
            //logger.debug(urlh.domain + " is no Hochschule");
        }
        urlh.count = 1;
        urls.add(urlh);
        //logger.debug("Leaving addDomain");
    }

    public void validateDomains() {

        for (UrlHochschule urlh :
                urls) {
            try {

                VereinfachtesResultSet vrs = mysqlConn.queryDomain(urlh.domain);
                urlh.hochschule = true;
                vrs.next();
                urlh.Hochschulname = vrs.getString("Hochschulname");
            } catch (NoResultsException e) {
                logger.debug(urlh.domain + " is no Hochschule");
            }
        }
    }

    public boolean isHochschule(String domain) {
        for (UrlHochschule urlh :
                urls) {
            if (urlh.domain.equals(domain)) {
                return urlh.hochschule;
            }
        }
        return false;
    }

    public UrlHochschule getHochschule(String domain) throws NoDomainFoundException, NoHochschuleException {
        for (UrlHochschule urlh :
                urls) {
            if (urlh.domain.equals(domain)) {
                if (urlh.hochschule) {
                    return urlh;
                } else {
                    throw new NoHochschuleException("Domain " + domain + " is no Hochschule");
                }
            }
        }
        throw new NoDomainFoundException("Cannot find domain " + domain + " in urls");
    }

}
