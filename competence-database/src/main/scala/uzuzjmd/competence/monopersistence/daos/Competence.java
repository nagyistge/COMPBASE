package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.Dao;
import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.monopersistence.HasDefinition;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class Competence extends DaoAbstractImpl implements HasDefinition {

    private Boolean compulsory;

    public Competence(String id) {
        super(id);
    }

    @Override
    public String getDefinition() {
        return this.getId();
    }

    public void addSuggestedCompetenceRequirement(Competence competence) throws Exception {
        createEdgeWith(competence, CompObjectProperties.SuggestedCompetencePrerequisiteOf);
    }

    public List<Competence> getSuggestedCompetenceRequirements() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.SuggestedCompetencePrerequisiteOf, Competence.class);
    }

    public void addRequiredCompetence(Competence competence) throws Exception {
        deleteEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf);
        createEdgeWith(competence, CompObjectProperties.PrerequisiteOf);
    }

    public void addNotRequiredCompetence(Competence competence) throws Exception {
        deleteEdgeWith(competence, CompObjectProperties.PrerequisiteOf);
        createEdgeWith(competence, CompObjectProperties.NotPrerequisiteOf);
    }

    public List<Catchword> getCatchwords() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.CatchwordOf, Catchword.class);
    }

    public List<Competence> getRequiredCompetences() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.PrerequisiteOf, Competence.class);
    }

    public String[] getRequiredCompetencesAsArray() throws Exception {
        List<Competence> requiredCompetences = getRequiredCompetences();
        String[] result = new String[requiredCompetences.size()];
        int i = 0;
        for (Competence requiredCompetence : requiredCompetences) {
            result[i] = requiredCompetence.getDefinition();
            i++;
        }
        return result;
    }

    public Boolean isLinkedAsRequired() throws Exception {
        return !(getRequiredCompetences().isEmpty() && getAssociatedDaosAsRange(CompObjectProperties.PrerequisiteOf, Competence.class).isEmpty());
    }

    public Boolean isAllowed(User user) throws Exception {
        List<Competence> prerequisites = getAssociatedDaosAsDomain(CompObjectProperties.PrerequisiteOf, Competence.class);
        Boolean result = true;
        for (Competence prerequisite : prerequisites) {
            result = result && prerequisite.hasEdge(user, CompObjectProperties.UserHasPerformed);
        }
        return result;
    }


    public void addSuperCompetence(Competence superCompetence) throws Exception {
        createEdgeWith(this, CompObjectProperties.subClassOf);
    }

    public void removeSuperCompetence(Competence superCompetence) throws Exception {
        deleteEdgeWith(superCompetence, CompObjectProperties.subClassOf);
    }


    @Override
    public String toString() {
        return this.getId();
    }

    public SelfAssessment getAssessment(User user) throws Exception {
        return queryManager.getSelfAssessment(this, user);
    }

    public List<Competence> getShortestPathToSubCompetence(Competence subCompetence) throws Exception {
        return queryManager.getShortestSubClassPath(subCompetence.getId(), this.getId(), Competence.class);
    }

    public List<Catchword> getCatchwordsAsJava() throws Exception {
        return getCatchwords();
    }

    public void addCatchword(Catchword dao) throws Exception {
        dao.persist();
        dao.createEdgeWith(CompObjectProperties.CatchwordOf, this);
    }

    public void addLearningTemplate(LearningProjectTemplate learningTemplate) throws Exception {
        createEdgeWith(learningTemplate, CompObjectProperties.LearningProjectTemplateOf);
    }

    public List<Operator> getOperators() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.OperatorOf, Operator.class);
    }

    public void addCourseContext(CourseContext course) throws Exception {
        createEdgeWith(course, CompObjectProperties.CourseContextOf);
        addSuperCompetencesToCourse(this, course);
    }

    public void addSuperCompetencesToCourse(Competence competence, CourseContext course) throws Exception {
        List<Competence>  superCompetences = competence.listSuperClasses(Competence.class);
        for (Competence superCompetence : superCompetences) {
            superCompetence.createEdgeWith(course, CompObjectProperties.CourseContextOf);
        }
    }
}
