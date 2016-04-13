package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.mapper.rest.write.User2Ont;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.Role;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.service.rest.dto.UserData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class UserApiImpl {

    /**
     * user is identified by email only.
     * <p/>
     * return all user resources is not possible at the time because of privacy reasons
     *
     * @param courseId
     * @param role
     * @return
     */
    @Path("/users")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getUsers(
            @QueryParam("courseId") String courseId,
            @QueryParam("role") String role) {
        // get all users enroled in a certain course or with a certain role
        throw new NotImplementedError();
    }


    /**
     * returns the full user with the id given.
     *
     * Be careful that users who have not been added via this interface might only be persisted by id (normally email),
     * because they are a reference to another identity store such as a cms or a lms system.
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Path("/users/{userId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        User result = user.getFullDao();
        UserData data = new UserData(result.getId(), result.getPrintableName(), null, null);
        return Response.status(200).entity(data).build();
    }

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

    @Path("/users/{userId}")
    @DELETE
    public Response deleteUser(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        user.delete();
        return Response.ok("user deleted").build();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     *
     * @param userId
     * @return
     */
    @Path("/users/{userId}/delete")
    @POST
    public Response deleteUserLegacy(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        user.delete();
        return Response.ok("user deleted").build();
    }

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param userId
     * @param data
     * @return
     */
    @Path("/users/{userId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUserLegacy(@PathParam("userId") String userId, UserData data) throws Exception {
        return addUser(data);
    }
}
