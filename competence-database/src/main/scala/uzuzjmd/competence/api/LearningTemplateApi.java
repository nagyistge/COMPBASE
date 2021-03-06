package uzuzjmd.competence.api;

import uzuzjmd.competence.service.rest.dto.LearningTemplateData;
import uzuzjmd.competence.shared.StringList;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 15.04.2016.
 */
public interface LearningTemplateApi {

    StringList getLearningTemplates();


    Response addLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);


    Response deleteLearningTemplate(@PathParam("learningtemplateId") String learningtemplateId);

    /**
     * Use this legacy method for browsers who do not support http delete
     * @param learningtemplateId
     * @return
     */
    Response deleteLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId);

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param learningtemplateId
     * @param data
     * @return
     */
    Response addLearningTemplateLegacy(@PathParam("learningtemplateId") String learningtemplateId, LearningTemplateData data);
}
