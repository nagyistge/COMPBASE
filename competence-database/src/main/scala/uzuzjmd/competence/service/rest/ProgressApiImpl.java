package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.rest.read.Ont2UserProgress;
import uzuzjmd.competence.shared.dto.UserCompetenceProgress;
import uzuzjmd.competence.shared.dto.UserProgress;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by dehne on 30.05.2016.
 */

@Path("/api1")
public class ProgressApiImpl {

    @Path("/progress/{userId}/courses/{courseId}")
    @GET
    public UserProgress getUserProgress(@PathParam("userId") String userId, @PathParam("courseId") String courseId) {
        return Ont2UserProgress.convert2(userId, courseId);
    }

    @Path("/progress/{userId}/competences/{competenceId}")
    @GET
    public UserCompetenceProgress getUserCompetenceProgress(@PathParam("userId") String userId, @PathParam("competenceId") String competenceId) {
        return Ont2UserProgress.convert(competenceId, userId);
    }

    @Path("/progress/{userId}")
    @PUT
    public void updateOrCreateUserProgress(@PathParam("userId") String userId, UserProgress userProgress) {
        // TODO implement user Progress
    }

    @Path("/progress/{userId}/competences/{competenceId}")
    @PUT
    public void updateOrCreateUserProgress(@PathParam("userId") String userId, @PathParam("competenceId") String competenceId, UserCompetenceProgress userProgress) {
        // TODO implement user Progress
    }

}
