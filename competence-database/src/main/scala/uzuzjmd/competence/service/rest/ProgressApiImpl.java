package uzuzjmd.competence.service.rest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.mapper.rest.read.Ont2UserProgress;
import uzuzjmd.competence.mapper.rest.write.UserProgress2Ont;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.shared.progress.UserCompetenceProgress;
import uzuzjmd.competence.shared.progress.UserProgress;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 30.05.2016.
 */

@Path("/api1")
public class ProgressApiImpl {

    private Logger logger = LogManager.getLogger(getClass());

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/progress/{userId}/courses/{courseId}")
    @GET
    public UserProgress getUserProgress(@PathParam("userId") String userId, @PathParam("courseId") String courseId) throws Exception {
        checkUserExists(userId);
        return Ont2UserProgress.convert2(userId, courseId);
    }

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/progress/{userId}/competences/{competenceId}")
    @GET
    public UserCompetenceProgress getUserCompetenceProgress(@PathParam("userId") String userId, @PathParam("competenceId") String competenceId) throws Exception {
        checkUserExists(userId);
        UserCompetenceProgress result = Ont2UserProgress.convert(competenceId, userId);
        return result;
    }

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/progress/{userId}")
    @PUT
    public Response updateOrCreateUserProgress(@PathParam("userId") String userId, UserProgress userProgress) throws Exception {
        checkUserExists(userId);
        UserProgress2Ont.convert(userProgress, (User) new User(userId).getFullDao());
        return Response.ok("progress updated").build();
    }



    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/progress/{userId}/competences/{competenceId}")
    @PUT
    public Response updateOrCreateUserProgress(@PathParam("userId") String userId, @PathParam("competenceId") String competenceId, UserCompetenceProgress userProgress) throws Exception {
        checkUserExists(userId);
        UserProgress2Ont.convert(new UserProgress(userProgress), new User(userId));
        return Response.ok("progress updated").build();
    }

    private void checkUserExists(@PathParam("userId") String userId) throws Exception {
        if (!new User(userId).exists()) {
            logger.error("user does not exist in database when trying to update user progress");
            throw new WebApplicationException(new Exception("user does not exist"));
        }
    }

}
