package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.rest.read.Ont2LearningTemplates;
import uzuzjmd.competence.mapper.rest.read.Ont2SelectedLearningTemplate;
import uzuzjmd.competence.mapper.rest.write.DeleteTemplateInOnt;
import uzuzjmd.competence.mapper.rest.write.LearningTemplateToOnt;
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate;
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
    public StringList getLearningTemplates(@QueryParam("userId") String userId, @QueryParam("courseId") String courseId) {
        if (userId != null) {
            return Ont2SelectedLearningTemplate.convert(new LearningTemplateData(userId, courseId, null));
        } else {
            StringList learningTemplates = Ont2LearningTemplates
                    .convert();
            return learningTemplates;
        }
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        data.setSelectedTemplate(learningtemplateId);
        LearningTemplateToOnt.convert(data);
        return Response.ok().build();
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, @QueryParam(value = "userId") String userName) throws Exception {
        if (userName != null) {
            DeleteTemplateInOnt.convert(new LearningTemplateData(userName, null, learningtemplateId));
        }
        return deleteLearningTemplateIntern(learningtemplateId);
    }

    private Response deleteLearningTemplateIntern(@PathParam("learningtemplateId") String learningtemplateId) throws Exception {
        LearningProjectTemplate learningProjectTemplate = new LearningProjectTemplate(learningtemplateId);
        learningProjectTemplate.delete();
        return Response.ok().build();
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}/delete")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId) throws Exception {
        return deleteLearningTemplateIntern(learningtemplateId);
    }

    @Override
    @Path("/learningtemplates/{learningtemplateId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data) {
        data.setSelectedTemplate(learningtemplateId);
        LearningTemplateToOnt.convert(data);
        return Response.ok().build();
    }


}
