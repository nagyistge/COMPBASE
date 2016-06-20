package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.service.rest.*;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.shared.StringList;
import uzuzjmd.competence.shared.dto.ActivityEntry;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dehne on 16.06.2016.
 */
public class ActivityApiTest extends JerseyTest {

    //String activityUrl = "agazigaabuggat";
    String activityUrl = "http://meinewunderschöneAktivität";
    String competenceId = "Ich kann programmieren";

    @Override
    protected javax.ws.rs.core.Application configure() {
        DBInitializer.init();
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class, ActivityApiImpl.class);
    }

    @Test
    public void createActivity() {



        ActivityEntry activityEntry = new ActivityEntry("AktivitätenAktivitäten", null, null, activityUrl);
        Response result = target("/api1/activities").request(MediaType.TEXT_PLAIN).put(Entity.entity(activityEntry, MediaType.APPLICATION_JSON));
        assertTrue(result.getStatus() == 200);
    }

    @Test
    public void linkActivityToCompetence() {
        // create competence first
        CompetenceData data = new CompetenceData("programmieren", Arrays.asList(new String[]{"programmieren", "tabalugatv"}), null, null, null, competenceId);
        Response result = target("/api1/competences/"+data.getForCompetence()).request(MediaType.TEXT_PLAIN).put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(result.getStatus() == 200);


        createActivity();
        // create Link

        //ActivityEntry activityEntry = new ActivityEntry(null, null, null, activityUrl);
        Response result2 = target("/api1/activities/links/competences/" + competenceId).request(MediaType.TEXT_PLAIN).post(Entity.entity(activityUrl, MediaType.APPLICATION_JSON));
        assertTrue(result2.getStatus() == 200);


    }

    @Test
    public void linkUserToCompetence() {

    }

    @Test
    public void suggestActivity() {

    }

    @Test
    public void linkUserToActivity() {

    }

    @Test
    public void recommendCompetence() {

        linkActivityToCompetence();

        //ActivityEntry activityEntry = new ActivityEntry(null, null, null, activityUrl);
        StringList result2 = target("/api1/activities/links/competences").queryParam("activityId", activityUrl).request(MediaType.APPLICATION_JSON).get(StringList.class);
        //assertTrue(result2.getStatus() == 200);
        assertFalse(result2.getData().isEmpty());
    }

    @Test
    public void createEvidence() {

    }


}
