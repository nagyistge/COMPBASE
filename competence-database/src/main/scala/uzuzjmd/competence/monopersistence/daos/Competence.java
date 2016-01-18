package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.Contexts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dehne on 11.01.2016.
 */
public class Competence extends AbstractCompetence implements HasDefinition, TreeLike {

    public Boolean compulsory;

    public Competence(String id) {
        super(id);
        compulsory = false;
    }

    public Competence(String id, Boolean compulsory) {
        super(id);
        this.compulsory = compulsory;
    }

    public Competence(String id, LearningProjectTemplate learningProject) {
        super(id);
        this.compulsory = false;
        this.learningProject = learningProject;
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
        return getAssociatedDaosAsRange(CompObjectProperties.CatchwordOf, Catchword.class);
    }

    public List<Competence> getRequiredCompetences() throws Exception {
        return getAssociatedDaosAsRange(CompObjectProperties.PrerequisiteOf, Competence.class);
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
        createEdgeWith(CompObjectProperties.subClassOf, superCompetence);
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
        Set<Competence> superCompetences = competence.listSuperClasses(Competence.class);
        for (Competence superCompetence : superCompetences) {
            superCompetence.createEdgeWith(course, CompObjectProperties.CourseContextOf);
        }
    }

    public Boolean isSuperClassOf(Competence subCompetence) {
        try {
            return listSubClasses(this.getClass()).contains(subCompetence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isSubClassOf(Competence superCompetence) throws Exception {
        Set<? extends Competence> result = listSuperClasses(this.getClass());
        try {
            return result.contains(superCompetence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteTree() throws Exception {
        Set<Competence> subClasses = listSubClasses();
        for (Competence subClass : subClasses) {
            deleteTree();
        }
        this.delete();
    }

    @Override
    public Dao persist() throws Exception {
        super.persist();
        createEdgeWith(CompObjectProperties.subClassOf, new Competence(DBInitializer.COMPETENCEROOT));
        CourseContext universityContext = new CourseContext(Contexts.university);
        createEdgeWith(universityContext, CompObjectProperties.CourseContextOf);
        if (learningProject != null) {
            createEdgeWith(learningProject, CompObjectProperties.LearningProjectTemplateOf);
        }
        return this;
    }


    public Set<Competence> listSubClasses() throws Exception {
        return (HashSet<Competence>) super.listSubClasses(getClass());
    }
}
