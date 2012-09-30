import java.io.File;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;

public class DBPathTest {

	@Test
	public void shouldReturnCompletePathWithoutClassName() {
		Neo4JStarter.initPaths();

		System.out.println("DB: " + Neo4JStarter.DATABASE_PATH);
		System.out.println("Props: " + Neo4JStarter.PROPERTIES_PATH);
	}
}
