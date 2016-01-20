package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.persistence.ontology.Edge;
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
        createEdgeWith(competence, Edge.SuggestedCompetencePrerequisiteOf);
    }

    public List<Competence> getSuggestedCompetenceRequirements() throws Exception {
        List<Competence> result =  getAssociatedDaosAsDomain(Edge.SuggestedCompetencePrerequisiteOf, Competence.class);
        return result;
    }

    public void addRequiredCompetence(Competence competence) throws Exception {
        deleteEdgeWith(competence, Edge.NotPrerequisiteOf);
        createEdgeWith(competence, Edge.PrerequisiteOf);
    }

    public void addNotRequiredCompetence(Competence competence) throws Exception {
        deleteEdgeWith(competence, Edge.PrerequisiteOf);
        createEdgeWith(competence, Edge.NotPrerequisiteOf);
    }

    public List<Catchword> getCatchwords() throws Exception {
        return getAssociatedDaosAsRange(Edge.CatchwordOf, Catchword.class);
    }

    public List<Competence> getRequiredCompetences() throws Exception {
        return getAssociatedDaosAsRange(Edge.PrerequisiteOf, Competence.class);
    }

    public List<Competence> getSuggestedRequiredCompetences() throws Exception {
        return getAssociatedDaosAsRange(Edge.SuggestedCompetencePrerequisiteOf, Competence.class);
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
        return !(getRequiredCompetences().isEmpty());
    }

    public Boolean isLinkedAsSuggestedRequired() throws Exception {
        return !(getSuggestedCompetenceRequirements().isEmpty());
    }

    public Boolean isAllowed(User user) throws Exception {
        List<Competence> prerequisites = getAssociatedDaosAsDomain(Edge.PrerequisiteOf, Competence.class);
        Boolean result = true;
        for (Competence prerequisite : prerequisites) {
            result = result && prerequisite.hasEdge(user, Edge.UserHasPerformed);
        }
        return result;
    }


    public void addSuperCompetence(Competence superCompetence) throws Exception {
        createEdgeWith(Edge.subClassOf, superCompetence);
    }

    public void removeSuperCompetence(Competence superCompetence) throws Exception {
        deleteEdgeWith(superCompetence, Edge.subClassOf);
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
        dao.createEdgeWith(Edge.CatchwordOf, this);
    }

    public void addLearningTemplate(LearningProjectTemplate learningTemplate) throws Exception {
        createEdgeWith(learningTemplate, Edge.LearningProjectTemplateOf);
    }

    public List<Operator> getOperators() throws Exception {
        return getAssociatedDaosAsDomain(Edge.OperatorOf, Operator.class);
    }

    public void addCourseContext(CourseContext course) throws Exception {
        createEdgeWith(course, Edge.CourseContextOf);
        addSuperCompetencesToCourse(this, course);
    }

    public void addSuperCompetencesToCourse(Competence competence, CourseContext course) throws Exception {
        Set<Competence> superCompetences = competence.listSuperClasses(Competence.class);
        for (Competence superCompetence : superCompetences) {
            superCompetence.createEdgeWith(course, Edge.CourseContextOf);
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
        createEdgeWith(Edge.subClassOf, new Competence(DBInitializer.COMPETENCEROOT));
        CourseContext universityContext = new CourseContext(Contexts.university);
        createEdgeWith(universityContext, Edge.CourseContextOf);
        if (learningProject != null) {
            createEdgeWith(learningProject, Edge.LearningProjectTemplateOf);
        }
        return this;
    }


    public Set<Competence> listSubClasses() throws Exception {
        return (HashSet<Competence>) super.listSubClasses(getClass());
    }
}
