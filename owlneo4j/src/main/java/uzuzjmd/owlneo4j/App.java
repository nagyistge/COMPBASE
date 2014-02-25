package uzuzjmd.owlneo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.rest.graphdb.RestGraphDatabase;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {    						
		GraphDatabaseService test = new RestGraphDatabase("http://localhost:7474/db/data");
		test.index().forNodes("nodes");
    	
    }
}
