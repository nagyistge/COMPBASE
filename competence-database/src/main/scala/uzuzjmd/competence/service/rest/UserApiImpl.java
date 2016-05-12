package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.evidence.service.MoodleEvidenceRestServiceImpl;
import uzuzjmd.competence.evidence.service.moodle.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleEvidenceList;
import uzuzjmd.competence.evidence.service.moodle.SimpleMoodleService;
import uzuzjmd.competence.evidence.service.rest.EvidenceServiceRestServerImpl;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.Role;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.service.rest.dto.UserData;
import uzuzjmd.competence.shared.dto.UserCourseListResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class UserApiImpl implements uzuzjmd.competence.api.UserApi {

    /**
     * @param courseId the course the users are fetched for
     * @param userName
     * @param password
     * @return
     */
    @Path("/users")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public java.util.List<UserData> getUsers(
            @QueryParam("courseId") String courseId,
            @QueryParam("userName") String userName, @QueryParam("password") String password) throws Exception {
        List<UserData> result = new ArrayList<>();
        if (userName == null) {
            throw new WebApplicationException("username must be set");
        }
        if (password == null) {
            throw new WebApplicationException("password must be set");
        }
        SimpleMoodleService simpleMoodleService = new SimpleMoodleService(userName, password);
        MoodleEvidenceList moodleEvidenceList = simpleMoodleService.getMoodleEvidenceList(courseId);
        for (MoodleEvidence moodleEvidence : moodleEvidenceList) {
            UserData userData = new UserData(moodleEvidence.getUserId(), moodleEvidence.getUsername(), courseId, "student", "moodle");
            result.add(userData);
        }
        Set<User> allInstances = User.getAllInstances(User.class);
        for (User user : allInstances) {
            UserData userData = new UserData(user.getId(), user.getPrintableName(), courseId, "student", "db");
            result.add(userData);
        }
        return result;
    }


    @Override
    @Path("/users/{userId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") String userId) throws Exception {
        User user = new User(userId);
        User result = user.getFullDao();
        UserData data = new UserData(result.getId(), result.getPrintableName(), null, null, null);
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
        User user = new User(data.getUser(), role, data.getPrintableName(), data.getLmsSystems(), new CourseContext(data.getCourseContext()));
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
    public UserCourseListResponse getCoursesForUser(@PathParam("userId") String userId, @QueryParam("password") String password) {
        userId = EvidenceServiceRestServerImpl.checkLoginisEmail(userId);
        MoodleEvidenceRestServiceImpl moodleEvidenceRestService = new MoodleEvidenceRestServiceImpl();
        return moodleEvidenceRestService.getCourses(LMSSystems.moodle.toString(), "university", userId, password);
    }

    @Override
    @Path("/users/{userId}/exists")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Boolean checksIfUserExists(@PathParam("userId") String userId, @QueryParam("password") String password) {
        userId = EvidenceServiceRestServerImpl.checkLoginisEmail(userId);
        MoodleEvidenceRestServiceImpl moodleEvidenceRestService = new MoodleEvidenceRestServiceImpl();
        return moodleEvidenceRestService.exists(LMSSystems.moodle.toString(), "university", userId, password);
    }

    @Path("/users/{userId}/competences")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getCompetencesForUser(@PathParam("userId") String userId) throws Exception {
        List<String> result = new ArrayList<>();
        User user = new User(userId);
        List<Competence> competencesLearned = user.getCompetencesLearned();
        for (Competence competence : competencesLearned) {
            result.add(competence.getDefinition());
        }
        return result;
    }
}
