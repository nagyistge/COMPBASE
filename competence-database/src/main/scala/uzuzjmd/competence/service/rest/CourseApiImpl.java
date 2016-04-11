package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.CourseData;
import uzuzjmd.competence.service.rest.dto.LearningTemplateData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class CourseApiImpl {

    @Path("/course")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getCourses(CourseData data) {
        throw new NotImplementedError();
    }

    @Path("/course/{courseId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCourse(@PathParam("courseId") String courseId, CourseData data) {
        throw new NotImplementedError();
    }

    @Path("/course/{courseId}")
    @DELETE
    public Response deleteCourse(@PathParam("courseId") String courseId) {
        throw new NotImplementedError();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     *
     * @param courseId
     * @return
     */
    @Path("/course/{courseId}/delete")
    @POST
    public Response deleteCourseLegacy(@PathParam("courseId") String courseId) {
        throw new NotImplementedError();
    }

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param courseId
     * @param data
     * @return
     */
    @Path("/course/{courseId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCourseLegacy(@PathParam("courseId") String courseId, CourseData data) {
        throw new NotImplementedError();
    }


}
