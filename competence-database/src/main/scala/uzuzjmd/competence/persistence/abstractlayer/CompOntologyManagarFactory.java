package uzuzjmd.competence.persistence.abstractlayer;

import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.neo4j.CompOntologyManagerNeo4jImpl;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;

/**
 * Created by dehne on 11.12.2015.
 */
public class CompOntologyManagarFactory {

    public static CompOntologyManager createJenaManager(){
        return new CompOntologyManagerJenaImpl();
    }

    public static CompOntologyManager createNeo4jManager(){
        return new CompOntologyManagerNeo4jImpl();
    }

    public static CompOntologyManager createManager() {
        if (MagicStrings.neo4jEnabled) {
            return createNeo4jManager();
        } else {
            return createJenaManager();
        }
    }

}
