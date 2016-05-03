package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.service.rest.CompetenceApiImpl;
import uzuzjmd.competence.service.rest.LearningTemplateApiImpl;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.shared.StringList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dehne on 19.04.2016.
 */
public class SimpleTest extends JerseyTest {


    @Override
    protected javax.ws.rs.core.Application configure() {
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class);
    }

    @Test
    public void test() {
        final StringList hello = target("/api1/learningtemplates").request().get(StringList.class);
        assertFalse(hello == null);
    }

    @Test
    public void testSimilaritiesInterface() throws InterruptedException {
        String competenceString = "Die Studierenden vergleichen zwei Sätze anhand ihrer Bausteine";
        CompetenceData data = new CompetenceData("vergleichen", Arrays.asList(new String[] {"vergleichen", "Sätze", "Bausteine"}),null, null, null, competenceString);
        Response post = target("/api1/competences/"+competenceString).request().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(post.getStatus() == 200);
        String competenceString2 = "Die Studierenden vergleichen drei Sätze anhand ihrer Bausteine";
        Response post1 = target("/api1/competences/" + competenceString2).request().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        assertTrue(post1.getStatus() == 200);

        Thread.sleep(10000l);
        java.util.List<String> result = target("/api1/competences/semblances/"+competenceString).request().get(java.util.List.class);
        assertTrue(result.contains(competenceString2));
    }

   /* @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new InMemoryTestContainerFactory();
    }*/
}
