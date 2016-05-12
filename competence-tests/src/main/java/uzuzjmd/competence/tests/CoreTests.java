package uzuzjmd.competence.tests;

import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.persistence.dao.Role;
import uzuzjmd.competence.service.rest.CompetenceApiImpl;
import uzuzjmd.competence.service.rest.LearningTemplateApiImpl;
import uzuzjmd.competence.service.rest.UserApiImpl;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.service.rest.dto.UserData;
import uzuzjmd.competence.shared.StringList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dehne on 19.04.2016.
 */
public class CoreTests extends JerseyTest {


    @Override
    protected javax.ws.rs.core.Application configure() {
        DBInitializer.init();
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class);
    }

    @Test
    public void test() {
        final StringList hello = target("/api1/learningtemplates").request().get(StringList.class);
        assertFalse(hello == null);
    }

    //@Test
    public void testSimilaritiesInterface() throws InterruptedException {
        String competenceString = "Die Studierenden vergleichen zwei Sätze anhand ihrer Bausteine";
        CompetenceData data = new CompetenceData("vergleichen", Arrays.asList(new String[]{"vergleichen", "Sätze", "Bausteine"}), null, null, null, competenceString);
        Response post = target("/api1/competences/" + competenceString).request().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(post.getStatus() == 200);
        String competenceString2 = "Die Studierenden vergleichen drei Sätze anhand ihrer Bausteine";
        Response post1 = target("/api1/competences/" + competenceString2).request().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(post1.getStatus() == 200);

        Thread.sleep(3000l);
//        java.util.List<String> result = target("/api1/competences/semblances/"+competenceString).request().get(java.util.List.class);
//        assertTrue(result.contains(competenceString2));
    }


    /* @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new InMemoryTestContainerFactory();
    }*/

    @Test
    public void testCompetenceDeletionAndHiding() throws InterruptedException {
        String competenceString = "Die Studierenden vergleichen y Sätze anhand ihrer Bausteine";
        String userEmail = "julian@stuff.com";

        UserData userData = new UserData(userEmail, "Julian Dehne", null, "student");
        Response post1 = target("/api1/users/" + userEmail).request().put(Entity.entity(userData, MediaType.APPLICATION_JSON));
        assertTrue(post1.getStatus() == 200);

        CompetenceData data = new CompetenceData("vergleichen", Arrays.asList(new String[]{"vergleichen", "Sätze", "Bausteine"}), null, null, null, competenceString);
        Response post = target("/api1/competences/" + competenceString).request().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(post.getStatus() == 200);

        Entity.entity(userEmail, MediaType.APPLICATION_JSON);
        Response delete = target("/api1/competences/" + competenceString + "/users/" + userEmail).request().delete();
        assertTrue(delete.getStatus() == 200);

        List get = target("/api1/competences").queryParam("courseId", "university").request().get(java.util.List.class);
        assertFalse(get.isEmpty());

        List get2 = target("/api1/competences").queryParam("courseId", "university").queryParam("userId", userEmail).request().get(java.util.List.class);
        assertTrue(get2.isEmpty());

    }
}
