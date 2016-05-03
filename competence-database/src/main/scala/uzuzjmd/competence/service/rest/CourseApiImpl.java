package uzuzjmd.competence.service.rest;

import com.google.common.collect.Lists;
import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.evidence.service.MoodleEvidenceRestServiceImpl;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.mapper.rest.read.Ont2SelectedCompetencesForCourse;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.service.rest.dto.CourseData;
import uzuzjmd.competence.shared.dto.UserTree;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class CourseApiImpl implements uzuzjmd.competence.api.CourseApi {

    @Override
    @Path("/courses")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<CourseData> getCourses(@QueryParam("competence") List<String> competences) {
        List<CourseData> result = new ArrayList<>();
        for (String competence : competences) {
            result.addAll(Lists.newArrayList(Ont2SelectedCompetencesForCourse.convertDao(competence)));
        }
        return result;
    }

    @Override
    @Path("/courses/{courseId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCourse(@PathParam("courseId") String courseId, CourseData data) throws Exception {
        return addCourseIntern(courseId, data);
    }

    private Response addCourseIntern(@PathParam("courseId") String courseId, CourseData data) throws Exception {
        CourseContext courseContext = new CourseContext(courseId);
        courseContext.persist();
        if (data.getCompetences() != null && !data.getCompetences().isEmpty()) {
            for (String string: data.getCompetences()) {
                Competence competence = new Competence(string);
                competence.persist();
                competence.addCourseContext(courseContext);
            }
        }
        return Response.ok("course added").build();
    }

    @Override
    @Path("/courses/{courseId}")
    @DELETE
    public Response deleteCourse(@PathParam("courseId") String courseId) throws Exception {
        CourseContext courseContext = new CourseContext(courseId);
        courseContext.delete();
        return Response.ok("course deleted").build();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     *
     * @param courseId
     * @return
     */
    @Override
    @Path("/courses/{courseId}/delete")
    @POST
    public Response deleteCourseLegacy(@PathParam("courseId") String courseId) throws Exception {
        CourseContext courseContext = new CourseContext(courseId);
        courseContext.delete();
        return Response.ok("course deleted").build();
    }

    /**
     * Use this legacy method for browsers who do not support http put.
     *
     * @param courseId
     * @param data
     * @return
     */
    @Override
    @Path("/courses/{courseId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCourseLegacy(@PathParam("courseId") String courseId, CourseData data) throws Exception {
        return addCourseIntern(courseId, data);
    }

    /**
     * the method can only be accessed if the authentication of a user in that course is provided for reasons
     * of privacy.
     *
     * @param courseId
     * @param userId
     * @param password
     * @return
     */
    @Override
    @Path("/courses/{courseId}/activities")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserTree[] getCoursesForUser(@PathParam("courseId") String courseId, @QueryParam("userId") String userId, @QueryParam("password") String password) {
        userId = EvidenceServiceRestServerImpl.checkLoginisEmail(userId);
        MoodleEvidenceRestServiceImpl moodleEvidenceRestService = new MoodleEvidenceRestServiceImpl();
        return moodleEvidenceRestService.getUserTree(courseId, LMSSystems.moodle.toString(), "university", userId, password);
    }
}
