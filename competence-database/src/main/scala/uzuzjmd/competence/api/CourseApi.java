package uzuzjmd.competence.api;

import uzuzjmd.competence.service.rest.dto.CourseData;
import uzuzjmd.competence.shared.dto.UserTree;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by dehne on 15.04.2016.
 */
public interface CourseApi {


    /**
     * Get all courses that are linked to the given competences.
     * @param competences
     * @return
     */
    @Path("/courses")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    List<CourseData> getCourses(@QueryParam("competence") List<String> competences);

    /**
     * Add a course to the persistence layer with data as payload and the courseId as param.
     * @param courseId
     * @param data
     * @return
     * @throws Exception
     */
    @Path("/courses/{courseId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCourse(@PathParam("courseId") String courseId, CourseData data) throws Exception;

    /**
     * Delete the course with the given ID from the database using DELETE
     * @param courseId
     * @return
     * @throws Exception
     */
    @Path("/courses/{courseId}")
    @DELETE
    Response deleteCourse(@PathParam("courseId") String courseId) throws Exception;

    /**
     * * Delete the course with the given ID from the database using POST
     * @param courseId
     * @return
     * @throws Exception
     */
    @Path("/courses/{courseId}/delete")
    @POST
    Response deleteCourseLegacy(@PathParam("courseId") String courseId) throws Exception;

    /**
     * get all the activities from a given course
     * @param courseId
     * @param data
     * @return
     * @throws Exception
     */
    @Path("/courses/{courseId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCourseLegacy(@PathParam("courseId") String courseId, CourseData data) throws Exception;

    /**
     * get all the activities from a given course in the form of a usertree
     * @param courseId
     * @param userId
     * @param password
     * @return
     */
    @Path("/courses/{courseId}/activities")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    UserTree[] getCoursesForUser(@PathParam("courseId") String courseId, String userId, String password);
}
