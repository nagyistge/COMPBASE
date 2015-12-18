package uzuzjmd.competence.persistence.neo4j.reasoning;

/**
 * Created by dehne on 18.12.2015.
 */
public class Neo4jRules {

    public static final String getCompulsoryInheritance() {
        return "MATCH (a:Course)-[r:CompulsoryOf]->(b)-[r2:individualOf]->(d:Competence)-[r3:subClassOf*]->(e:Competence{id:'Competence'}),(f)-[r4:individualOf]->(e:Competence) CREATE UNIQUE (a)-[r5:CompulsoryOf]->(f)";
    }

    public static final String catchwordTransitivity() {
        return "MATCH (a:Catchword),(catchword:Catchword{id:'Catchword'}) WHERE NOT (a.id='Catchword') CREATE UNIQUE (a)-[r:subClassOf]->(catchword)";
    }

    public static final String operatorTransitivity() {
        return "MATCH (a:Operator),(operator:Operator{id:'Operator'}) WHERE NOT (a.id='Operator') CREATE UNIQUE (a)-[r:subClassOf]->(operator)";
    }

    public static final String getPrerequisiteConclusion() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(b)-[r2:PrerequisiteOf]->(c) WHERE NOT(a=c) CREATE UNIQUE (a)-[r3:PrerequisiteOf]->(c)";
    }

    public static final String getSuggestedPrerequisiteConclusion() {
        return "MATCH (a)-[r1:SuggestedPrerequisiteOf]->(b)-[r2:SuggestedPrerequisiteOf]->(c) WHERE NOT(a=c) CREATE UNIQUE (a)-[r3:SuggestedPrerequisiteOf]->(c)";
    }

    public static final String getCompulsoryToSuggestedRequisiste() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(b) WHERE NOT(a=b) CREATE UNIQUE (a)-[r3:SuggestedPrerequisiteOf]->(c)";
    }

    public static final String getNotRequireTransition1() {
        return "MATCH (a)-[r1:NotPrerequisiteOf]->(b), (b)-[r2:PrerequisiteOf]->(c), (a)-[r3:PrerequisiteOf]->(c) DELETE r3";
    }

    public static final String getNotRequireTransition2() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(b), (b)-[r2:NotPrerequisiteOf]->(c), (a)-[r3:PrerequisiteOf]->(c) DELETE r1";
    }

    public static final String getNotRequireTransition3() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(b), (b)-[r2:PrerequisiteOf]->(c), (a)-[r3:NotPrerequisiteOf]->(c) DELETE r2";
    }

    public static final String getNotAllowedCreationRule() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(b), (user:User) CREATE UNIQUE (user)-[r2:NotAllowedToView]->(a)";
    }

    public static String getNotSelfReferencedPrerequisiteCreationRule() {
        return "MATCH (a)-[r1:PrerequisiteOf]->(a) DELETE r1";
    }

    public static String getNotSelfReferencedSuggestedPrerequisiteCreationRule() {
        return "MATCH (a)-[r1:SuggestedPrerequisiteOf]->(a) DELETE r1";
    }

    public static String getNotAllowedToViewCreationRule() {
        return "MATCH (a)-[r1:NotAllowedToView]->(a) DELETE r1";
    }

    public static String getNotAllowedLinkRule() {
        return "MATCH (user:User)-[r1:NotAllowedToView]->(a), (a:Competence)-[r2:PrerequisiteOf]->(b), (user:User)-[r3:UserOfLink]->(a:AbstractEvidenceLink) DELETE r1";
    }

    public String superCompetencesHaveSameCourseContext() {
        return "MATCH (courseContext:Course)-[r1:CourseContextOf]->(competence),(competence)-[r2:individualOf]->(competenceClass:Competence), (competence2)-[r3:individualOf]->(competenceClass2:Competence) WHERE NOT(courseContext.id='university') AND NOT(competence.id=competence2.id) CREATE UNIQUE (courseContext)-[r4:CourseContextOf]->(competence2)";
    }


    public String createPerformanceLinks() {
        return "MATCH (user:User)-[r1:UserOfLink]->(abstractEvidenceLink:AbstractEvidenceLink),(abstractEvidenceLink:AbstractEvidenceLink)-[r2:linksCompetence]->(competence) CREATE UNIQUE (user)-[r3:UserHasPerformed]->(competence)";
    }

    public String recommendCourse() {
        return  "MATCH (user:User)-[r1:UserHasPerformed]->()-[r2:SuggestedCompetencePrerequisiteOf]->(competence2), (course:Course)-[r3:SuggestedCourseForCompetence]->(competence2) CREATE UNIQUE (course)-[r4:CommendedCourseForUser]->(user)";
    }
}
