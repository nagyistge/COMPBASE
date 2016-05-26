package uzuzjmd.competence.tests;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.service.rest.CompetenceApiImpl;
import uzuzjmd.competence.service.rest.CourseApiImpl;
import uzuzjmd.competence.service.rest.EvidenceApiImpl;
import uzuzjmd.competence.service.rest.LearningTemplateApiImpl;
import uzuzjmd.competence.service.rest.UserApiImpl;

public class SubCompetencenTest extends JerseyTest {

	@Override
    protected javax.ws.rs.core.Application configure() {
        DBInitializer.init();
        return new ResourceConfig(LearningTemplateApiImpl.class, CompetenceApiImpl.class, UserApiImpl.class, CourseApiImpl.class, EvidenceApiImpl.class, EvidenceServiceRestServerImpl.class);
    }
	
	@Test
	public void testCreateSubCompetence() {
		final int status = createSubCompetence("creating api", "unter Kompetence", "op1", "catW 1");
		final int status1 = createSubCompetence("creating api", "unter Kompetence 1", "op1", "catW 1");
		final int status2 = createSubCompetence("creating api", "unter Kompetence 2", "op1", "catW 1");
		
		String subCompetencen = getSubCompetence("creating api");
		
		System.out.println(subCompetencen);
		
		Assert.assertEquals(200, status);
		Assert.assertEquals(200, status1);
		Assert.assertEquals(200, status2);
	}
	
	public int createSubCompetence(String superCompetence, String subCompetence, String operator, String ... catchwords) {
		final Response response = target("/competences/addOne/")
								.queryParam("competence", superCompetence)
								.queryParam("operator", operator)
								.queryParam("catchwords", (Object [])catchwords)
								.queryParam("subCompetences", subCompetence)
								.request().put(Entity.entity("", MediaType.APPLICATION_JSON));
		
		return response.getStatus();
	}
	
	public String getSubCompetence(String rootCompetence) {
		final String context = "university";
		final String response = target("/competences/competencetree/" + context + "/")
				.queryParam("rootCompetence", rootCompetence)
				.request().get(String.class);
		return response;
	}
}
