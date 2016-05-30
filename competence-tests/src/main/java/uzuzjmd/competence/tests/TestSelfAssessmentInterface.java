package uzuzjmd.competence.tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.service.rest.*;

/**
 * Created by dehne on 30.05.2016.
 */
public class TestSelfAssessmentInterface extends JerseyTest {

    @Override
    protected javax.ws.rs.core.Application configure() {
        DBInitializer.init();
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class);
    }

    @Test
    public void testCreateOrUpdateSelfAssessent() {

    }

    @Test
    public void testGetSelfAssessent() {

    }
}
