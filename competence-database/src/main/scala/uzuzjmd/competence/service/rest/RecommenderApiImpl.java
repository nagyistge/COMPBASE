package uzuzjmd.competence.service.rest;

/**
 * Created by dehne on 31.03.2016.
 */

import uzuzjmd.competence.recommender.Evidence;
import uzuzjmd.competence.recommender.RecommenderFactory;
import uzuzjmd.competence.shared.dto.UserCourseListItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("@Path(/api1/recommendations")
public class RecommenderApiImpl {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/competences/{userEmail}")
    public HashMap<String, Double> recommendCompetences(@PathParam("userEmail") String userEmail, @QueryParam("competenceToReach") String competenceToReach, @QueryParam("courseId") String courseId) {
        return RecommenderFactory.createCompetenceRecommender().recommendCompetences(userEmail, competenceToReach, courseId);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/activities/{userEmail}")
    public HashMap<Evidence, Double> recommendActivities(@PathParam("userEmail") String userEmail, @QueryParam("competenceToReach") String competenceToReach, @QueryParam("courseId") String courseId) {
        return RecommenderFactory.createActivityRecommender().recommendActivities(userEmail, competenceToReach, courseId);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/courses/{userEmail}")
    public HashMap<UserCourseListItem, Double> recommendCourses(@PathParam("userEmail") String userEmail) {
        return RecommenderFactory.createCourseRecommender().recommendCourse(userEmail);
    }
}
