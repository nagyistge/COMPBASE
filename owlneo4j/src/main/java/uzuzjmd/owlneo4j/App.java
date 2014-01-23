package uzuzjmd.owlneo4j;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	RestAPI restAPI = new RestAPIFacade("http://localhost:7474/db/data","test","test");    	
    }
}
