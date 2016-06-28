package uzuzjmd.competence.shared.progress;

import uzuzjmd.competence.shared.assessment.AbstractAssessment;
import uzuzjmd.competence.shared.competence.CompetenceLinksView;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dehne on 30.05.2016.
 */
@XmlRootElement
public class UserCompetenceProgress {

    // the competence the progress is referring to
    private String competence;
    // the activities linked to the competence with the given user
    private CompetenceLinksView[] competenceLinksView;
    // the self assessments of the user
    private List<AbstractAssessment> abstractAssessment;

    public UserCompetenceProgress() {
    }

    public UserCompetenceProgress(String competence, CompetenceLinksView[] competenceLinksView, List<AbstractAssessment> abstractAssessment) {
        this.competence = competence;
        this.competenceLinksView = competenceLinksView;
        this.abstractAssessment = abstractAssessment;
    }

    public UserCompetenceProgress(String competence, CompetenceLinksView[] competenceLinksView, AbstractAssessment abstractAssessment) {
        this.competence = competence;
        this.competenceLinksView = competenceLinksView;
        this.abstractAssessment = new ArrayList<AbstractAssessment>();
        this.abstractAssessment.add(abstractAssessment);
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public CompetenceLinksView[] getCompetenceLinksView() {
        return competenceLinksView;
    }

    public void setCompetenceLinksView(CompetenceLinksView[] competenceLinksView) {
        this.competenceLinksView = competenceLinksView;
    }

    public List<AbstractAssessment> getAbstractAssessment() {
        return abstractAssessment;
    }

    public void setAbstractAssessment(List<AbstractAssessment> abstractAssessment) {
        this.abstractAssessment = abstractAssessment;
    }
}
