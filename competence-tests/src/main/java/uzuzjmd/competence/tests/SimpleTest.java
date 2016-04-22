package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.service.rest.LearningTemplateApiImpl;
import uzuzjmd.competence.shared.StringList;

import static org.junit.Assert.assertFalse;

/**
 * Created by dehne on 19.04.2016.
 */
public class SimpleTest extends JerseyTest {


    @Override
    protected javax.ws.rs.core.Application configure() {
        return new ResourceConfig(LearningTemplateApiImpl.class);
    }

    @Test
    public void test() {
        final StringList hello = target("/api1/learningtemplates").request().get(StringList.class);
        assertFalse(hello == null);
    }

   /* @Override
    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new InMemoryTestContainerFactory();
    }*/
}
