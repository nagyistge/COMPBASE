package uzuzjmd.competence.api;

import uzuzjmd.competence.shared.learningtemplate.LearningTemplateData;
import datastructures.lists.StringList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 15.04.2016.
 */
public interface LearningTemplateApi {


    StringList getLearningTemplates(@QueryParam("userId") String userId, @QueryParam("courseId") String courseId);

    Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);


    Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, @QueryParam(value = "userId") String userName) throws Exception;

    /**
     * Use this legacy method for browsers who do not support http delete
     * @param learningtemplateId
     * @return
     */
    Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId) throws Exception;

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param learningtemplateId
     * @param data
     * @return
     */
    Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);
}
