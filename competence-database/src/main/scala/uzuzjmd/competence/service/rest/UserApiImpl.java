package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.UserData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class UserApiImpl {

    @Path("/user")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getUsers(UserData data) {
        throw new NotImplementedError();
    }

    @Path("/user/{userId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUser(@PathParam("userId") String userId, UserData data) {
        throw new NotImplementedError();
    }

    @Path("/user/{userId}")
    @DELETE
    public Response deleteUser(@PathParam("userId") String userId) {
        throw new NotImplementedError();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     *
     * @param userId
     * @return
     */
    @Path("/user/{userId}/delete")
    @POST
    public Response deleteUserLegacy(@PathParam("userId") String userId) {
        throw new NotImplementedError();
    }

    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @param userId
     * @param data
     * @return
     */
    @Path("/user/{userId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUserLegacy(@PathParam("userId") String userId, UserData data) {
        throw new NotImplementedError();
    }
}
