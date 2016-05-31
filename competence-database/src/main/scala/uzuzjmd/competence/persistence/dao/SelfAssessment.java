package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.shared.Assessment;
import uzuzjmd.competence.shared.dto.AbstractAssessment;

/**
 * Created by dehne on 11.01.2016.
 */
public class SelfAssessment extends AbstractSelfAssessment implements Cascadable{

    public Integer assessmentIndex = 0;
    public Boolean learningGoal = false;

    public SelfAssessment(String id) {
        super(id);
    }

 /*   public SelfAssessment(String id, Integer assessmentIndex, Boolean learningGoal) {
        super(id);
        this.assessmentIndex = assessmentIndex;
        this.learningGoal = learningGoal;
    }*/

    public SelfAssessment(Competence competence, User user, Integer assessmentIndex, Boolean learningGoal) {
        super(user.getId() + competence.getId());
        this.assessmentIndex = assessmentIndex;
        this.learningGoal = learningGoal;
        this.user = user;
        this.competence = competence;
    }

    @Override
    public void persistMore() throws Exception {
        this.persist();
        createEdgeWith(Edge.AssessmentOfCompetence, competence);
        createEdgeWith(Edge.AssessmentOfUser, user);
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
        createEdgeWith(Edge.AssessmentOfCompetence, competence);
    }

    public void addUserToAssessment(User user) throws Exception {
        createEdgeWith(Edge.AssessmentOfUser, user);
    }

    public AbstractAssessment toAbstractAssessment() {
        return new AbstractAssessment("eposScale", this.getAssessmentIndex(), new Assessment().getItems());
    }

    @Override
    public Dao persist() throws Exception {
        super.persist();
        if (this.user != null) {
            addUserToAssessment(this.user);
        }
        if (this.competence != null) {
            addCompetenceToAssessment(competence);
        }
        return this;
    }
}
