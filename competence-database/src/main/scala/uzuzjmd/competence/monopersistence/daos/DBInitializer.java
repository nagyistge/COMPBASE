package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManagerImpl;

/**
 * Created by dehne on 15.01.2016.
 */
public class DBInitializer {

    private static Boolean initialized = false;
    public final static String COMPETENCEROOT = "Kompetenz";
    public final static String OPERATORROOT = "Verb";
    public final static String CATCHWORDROOT = "Stichwort";

    public static void init() {
        if (!initialized) {
            try {
                new Competence(COMPETENCEROOT).persist();
                new Catchword(CATCHWORDROOT).persist();
                new Operator(OPERATORROOT).persist();
            } catch (Exception e) {
                e.printStackTrace();
            }
            initialized = true;
        }
    }
}
