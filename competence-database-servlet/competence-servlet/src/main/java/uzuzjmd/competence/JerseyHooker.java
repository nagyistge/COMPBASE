package uzuzjmd.competence;

import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.service.rest.CompetenceServiceRestJSON;
import uzuzjmd.competence.service.rest.CompetenceServiceRestXML;
import uzuzjmd.competence.service.rest.ResponseCorsFilter;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class JerseyHooker {
	static {		
		ResourceConfig resourceConfig = new DefaultResourceConfig(CompetenceServiceRestXML.class, CompetenceServiceRestJSON.class, EvidenceServiceRestServerImpl.class);
		resourceConfig.getContainerResponseFilters().add(ResponseCorsFilter.class);
	}
}
