package uzuzjmd.competence.api;

import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.LearningTemplateData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 15.04.2016.
 */
public interface LearningTemplateApi {
    @Path("/learningtemplate")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    List<String> getLearningTemplates(LearningTemplateData data);

    @Path("/learningtemplate/{learningtemplateId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);

    @Path("/learningtemplate/{learningtemplateId}")
    @DELETE
    Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId);

    /**
     * Use this legacy method for browsers who do not support http delete
     * @param learningtemplateId
     * @return
     */
    @Path("/learningtemplate/{learningtemplateId}/delete")
    @POST
    Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId);

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param learningtemplateId
     * @param data
     * @return
     */
    @Path("/learningtemplate/{learningtemplateId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);
}
