package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.Cascadable;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

/**
 * Created by dehne on 11.01.2016.
 */
public class SelfAssessment extends AbstractSelfAssessment implements Cascadable{

    private Integer assessmentIndex = 0;
    private Boolean learningGoal = false;

    public SelfAssessment(String id) {
        super(id);
    }

    public SelfAssessment(String id, Integer assessmentIndex, Boolean learningGoal) {
        super(id);
        this.assessmentIndex = assessmentIndex;
        this.learningGoal = learningGoal;
    }

    public SelfAssessment(Integer assessmentIndex, Boolean learningGoal, User user, Competence competence) {
        super(user.getId() + competence.getId());
        this.assessmentIndex = assessmentIndex;
        this.learningGoal = learningGoal;
        this.user = user;
        this.competence = competence;
    }

    @Override
    public void persistMore() throws Exception {
        this.persist();
        createEdgeWith(CompObjectProperties.AssessmentOfCompetence, competence);
        createEdgeWith(CompObjectProperties.AssessmentOfUser, user);
    }

    public Integer getAssessmentIndex() {
        return assessmentIndex;
    }

    public Boolean getLearningGoal() {
        return learningGoal;
    }

    public void setAssessmentIndex(Integer assessmentIndex) {
        this.assessmentIndex = assessmentIndex;
    }

    public void setLearningGoal(Boolean learningGoal) {
        this.learningGoal = learningGoal;
    }

    public void addCompetenceToAssessment(Competence competence) throws Exception {
        createEdgeWith(CompObjectProperties.AssessmentOfCompetence, competence);
    }

    public void addUserToAssessment(User user) throws Exception {
        createEdgeWith(CompObjectProperties.AssessmentOfUser, user);
    }
}
