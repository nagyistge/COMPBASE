package uzuzjmd.competence.service.rest;

/**
 * Created by dehne on 31.03.2016.
 */

import uzuzjmd.competence.mapper.rest.write.HandleLinkValidationInOnt;
import uzuzjmd.competence.mapper.rest.write.SuggestedActivityForCompetence2Ont;
import uzuzjmd.competence.mapper.rest.write.SuggestedCourseForCompetence2Ont;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.EvidenceActivity;
import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.recommender.Evidence;
import uzuzjmd.competence.recommender.RecommenderFactory;
import uzuzjmd.competence.service.rest.dto.LinkValidationData;
import uzuzjmd.competence.shared.dto.UserCourseListItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

/**
 * Root resource (exposed at "competences" path)
 */
@Path("/api1/recommendations")
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


    /**
     * Get competences linked to (courseId) context. (the moodle course Id)
     * <p/>
     * Returns all the competences recommended for a course context.
     *
     * @param courseId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/courses/{courseId}")
    public String[] getSuggestedCompetencesForCourse(
            @PathParam("courseId") String courseId) throws Exception {
        CourseContext context = new CourseContext(courseId);
        if (context.getAssociatedDaoIdsAsDomain(Edge.CourseContextOfCompetence) == null) {
            return new String[0];
        }
        List<String> result = context.getAssociatedDaoIdsAsDomain(Edge.CourseContextOfCompetence);
        result.remove("Kompetenz");
        return result.toArray(new String[0]);
    }


    /**
     * Get competences linked to (course) context.
     * <p/>
     * Returns all the courses linked to a certain competence as a suggestions
     *
     * @param competenceId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/competences/{competenceId}")
    public String[] getSuggestedCoursesForCompetence(
            @PathParam("competenceId") String competenceId) throws Exception {
        Competence context = new Competence(competenceId);

        if (context.getAssociatedDaoIdsAsRange(Edge.CourseContextOfCompetence) == null) {
            return new String[0];
        }
        List<String> result = context.getAssociatedDaoIdsAsRange(Edge.CourseContextOfCompetence);
        return result.toArray(new String[0]);
    }


    /**
     * The semantic of this interface is that an activity is linked to a competence as template.
     * This way the learner can navigate to the navigate if he/she wants to learn a specific set of competencies
     * @param competenceId should be the plain text string of the competence
     * @param activityId should be the url of the activity
     * @return
     * @throws Exception
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/competences/{competenceId}/activities/{activityId}")
    public Response createSuggestedActivityForCompetence(@PathParam("competenceId") String competenceId, @PathParam("activityId") String activityId) throws Exception {
        EvidenceActivity activity = new EvidenceActivity(activityId);
        activity.persist();
        Competence competence1 = new Competence(competenceId);
        competence1.persist();
        SuggestedActivityForCompetence2Ont.write(activityId, competenceId);
        return Response.ok("edge created").build();
    }

    /**
     * This returns a list of competencies linked to certain activity
     * @param activityId
     * @return
     * @throws Exception
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/activities/{activityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getCompetencesForSuggestedActivity(@PathParam("activityId") String activityId) throws Exception {
        EvidenceActivity activity = new EvidenceActivity(activityId);
        if (activity.getAssociatedDaoIdsAsDomain(Edge.SuggestedActivityForCompetence) == null) {
            return new String[0];
        }
        List<String> result = activity.getAssociatedDaoIdsAsDomain(Edge.SuggestedActivityForCompetence);
        return result.toArray(new String[0]);

    }

    /**
     * Deletes the link between the course and the given competence
     * @param competence
     * @param course
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/competences/{competenceId}/courses/{courseId}")
    public Response deleteSuggestedCourseForCompetence(@PathParam("competenceId") String competence, @PathParam("courseId") String course) {
        SuggestedCourseForCompetence2Ont.delete(course, competence);
        return Response.ok("edge deleted").build();
    }

    /**
     * Deletes the link between the activity and the given competence
     * @param competenceId
     * @param activityId
     * @return
     */
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @DELETE
    @Path("/competences/{competenceId}/activities/{activityId}")
    public Response deleteSuggestedActivityForCompetence(@PathParam("competenceId") String competenceId, @PathParam("activityId") String activityId) {
        SuggestedActivityForCompetence2Ont.delete(activityId, competenceId);
        return Response.ok("edge created").build();
    }





}
