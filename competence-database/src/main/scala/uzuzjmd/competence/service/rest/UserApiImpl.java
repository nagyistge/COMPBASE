package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.evidence.service.MoodleEvidenceRestServiceImpl;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.Role;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.service.rest.dto.UserData;
import uzuzjmd.competence.shared.dto.UserCourseListResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class UserApiImpl implements uzuzjmd.competence.api.UserApi {

    @Override
    @Path("/users")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getUsers(
            @QueryParam("courseId") String courseId,
            @QueryParam("role") String role) {
        // get all users enroled in a certain course or with a certain role
        throw new NotImplementedError();
    }


    @Override
    @Path("/users/{userId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        User result = user.getFullDao();
        UserData data = new UserData(result.getId(), result.getPrintableName(), null, null);
        return Response.status(200).entity(data).build();
    }

    @Override
    @Path("/users/{userId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUser(@PathParam("userId") String userId, UserData data) throws Exception {
        return addUser(data);
    }

    private Response addUser(UserData data) throws Exception {
        Role role = null;
        if (data.getRole() != null) {
            role = Role.valueOf(data.getRole());
        }
        User user = new User(data.getUser(), role , data.getPrintableName(), new CourseContext(data.getCourseContext()));
        user.persist();
        return Response.ok("user added").build();
    }

    @Override
    @Path("/users/{userId}")
    @DELETE
    public Response deleteUser(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        user.delete();
        return Response.ok("user deleted").build();
    }

    @Override
    @Path("/users/{userId}/delete")
    @POST
    public Response deleteUserLegacy(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        user.delete();
        return Response.ok("user deleted").build();
    }

    @Override
    @Path("/users/{userId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUserLegacy(@PathParam("userId") String userId, UserData data) throws Exception {
        return addUser(data);
    }

    @Override
    @Path("/users/{userId}/courses")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserCourseListResponse getCoursesForUser(@PathParam("userId") String userId, String password) {
        userId = EvidenceServiceRestServerImpl.checkLoginisEmail(userId);
        MoodleEvidenceRestServiceImpl moodleEvidenceRestService = new MoodleEvidenceRestServiceImpl();
        return moodleEvidenceRestService.getCourses(LMSSystems.moodle.toString(), "university", userId, password);
    }

    @Override
    @Path("/users/{userId}/exists")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Boolean checksIfUserExists(@PathParam("userId") String userId, String password) {
        userId = EvidenceServiceRestServerImpl.checkLoginisEmail(userId);
        MoodleEvidenceRestServiceImpl moodleEvidenceRestService = new MoodleEvidenceRestServiceImpl();
        return moodleEvidenceRestService.exists(LMSSystems.moodle.toString(), "university", userId, password);
    }


}
