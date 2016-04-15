package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData;
import uzuzjmd.competence.service.rest.dto.LearningTemplateData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class LearningTemplateApiImpl implements uzuzjmd.competence.api.LearningTemplateApi {

    @Override
    @Path("/learningtemplate")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getLearningTemplates(LearningTemplateData data){
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplate/{learningtemplateId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplate/{learningtemplateId}")
    @DELETE
    public Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplate/{learningtemplateId}/delete")
    @POST
    public Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplate/{learningtemplateId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        throw new NotImplementedError();
    }





}
