package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import uzuzjmd.competence.mapper.rest.read.Ont2LearningTemplates;
import uzuzjmd.competence.service.rest.dto.LearningTemplateData;
import uzuzjmd.competence.shared.StringList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class LearningTemplateApiImpl implements uzuzjmd.competence.api.LearningTemplateApi {

    @Override
    @Path("/learningtemplates")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public StringList getLearningTemplates(){
        StringList learningTemplates = Ont2LearningTemplates
                .convert();
        return learningTemplates;
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}")
    @DELETE
    public Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}/delete")
    @POST
    public Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId) {
        throw new NotImplementedError();
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        throw new NotImplementedError();
    }





}
