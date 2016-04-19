package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.service.rest.CompetenceApiImpl;
import uzuzjmd.competence.service.rest.LearningTemplateApiImpl;
import uzuzjmd.competence.shared.StringList;

import javax.ws.rs.core.Application;
import java.util.List;
import static org.junit.Assert.assertFalse;

/**
 * Created by dehne on 19.04.2016.
 */
public class SimpleTest extends JerseyTest {

 /*   @Path("/api1/competences/")
    public static class HelloResource {
        @GET
        public String getHello() {
            return "Hello World!";
        }
    }*/

    @Override
    protected Application configure() {
        return new ResourceConfig(LearningTemplateApiImpl.class);
    }

    @Test
    public void test() {
        final StringList hello = target("/learningtemplates").request().get(StringList.class);
        assertFalse(hello.getData().isEmpty());
    }
}
