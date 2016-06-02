package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.persistence.dao.SelfAssessment;
import uzuzjmd.competence.service.rest.*;
import uzuzjmd.competence.shared.dto.AbstractAssessment;
import uzuzjmd.competence.shared.dto.CommentEntry;
import uzuzjmd.competence.shared.dto.CompetenceLinksView;
import uzuzjmd.competence.shared.dto.UserCompetenceProgress;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dehne on 31.05.2016.
 */
public class ProgressApiTests extends JerseyTest {

    String competenceString = "Ich bin in der Lage eine Masterarbeit zu konzipieren";
    String user = "Mephisto";

    @Override
    protected javax.ws.rs.core.Application configure() {
        DBInitializer.init();
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class, ProgressApiImpl.class);
    }

    @Test
    public void createUserProgress() throws Exception {

        User userDao = new User(user);
        userDao.printableName = user;
        userDao.persist();


        // create evidence Link
        CompetenceLinksView competenceLinksView1 = new CompetenceLinksView();
        competenceLinksView1.setEvidenceTitel("Ich habe einen UI-Protoyp erstellt");
        competenceLinksView1.setEvidenceUrl("Http://meinefressewoistdasdenn");
        competenceLinksView1.setValidated(false);
        CommentEntry commentEntry = new CommentEntry("julian", "läuft gut", System.currentTimeMillis());
        competenceLinksView1.setComments(Arrays.asList(commentEntry));
        SelfAssessment assessment = new SelfAssessment(new Competence(competenceString), new User(user), 3, true);
        AbstractAssessment abstractAssessments1 = assessment.toAbstractAssessment();
        UserCompetenceProgress userCompetenceProgress = new UserCompetenceProgress(competenceString, new CompetenceLinksView[] {competenceLinksView1}, abstractAssessments1);
        Response response = target("/api1/progress/"+user+"/competences/"+competenceString).request().put(Entity.entity(userCompetenceProgress, MediaType.APPLICATION_JSON));
        assertTrue(response.getStatus() == 200);

    }


    @Test
    public void getUserProgress() {
        UserCompetenceProgress response = target("/api1/progress/"+user+"/competences/"+ competenceString).request().get(UserCompetenceProgress.class);
        assertFalse(response.getAbstractAssessment().isEmpty());
        assertFalse(response.getCompetenceLinksView().length == 0 );
        assertFalse(response.getCompetenceLinksView()[0].getComments().isEmpty());

    }

}
