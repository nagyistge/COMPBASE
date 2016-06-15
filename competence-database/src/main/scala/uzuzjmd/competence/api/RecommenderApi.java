package uzuzjmd.competence.api;

import uzuzjmd.competence.recommender.Evidence;
import uzuzjmd.competence.shared.dto.MapWrapper;
import uzuzjmd.competence.shared.dto.UserCourseListItem;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Created by dehne on 15.04.2016.
 */
public interface RecommenderApi {
    /**
     * returns all the competences recommended for a user given the users id (normally the userEmail)
     * @param userEmail
     * @param competenceToReach
     * @param courseId
     * @return
     */
    HashMap<String, Double> recommendCompetences(@PathParam("userEmail") String userEmail, @QueryParam("competenceToReach") String competenceToReach, @QueryParam("courseId") String courseId);

    /**
     * returns all the activities recommended for a user given the users id (normally the userEmail)
     * @param userEmail
     * @param competenceToReach
     * @param courseId
     * @return
     */
    HashMap<Evidence, Double> recommendActivities(@PathParam("userEmail") String userEmail, @QueryParam("competenceToReach") String competenceToReach, @QueryParam("courseId") String courseId);

    /**
     * returns all the courses recommended for a user given the users id (normally the userEmail)
     * @param userEmail
     * @return
     */
    MapWrapper<UserCourseListItem, Double> recommendCourses(@PathParam("userEmail") String userEmail);

    /**
     * Get competences linked to (courseId) context. (the moodle courseId Id)
     * <p/>
     * Returns all the competences recommended for a courseId context.
     *
     * @param courseId
     * @return
     */
    String[] getSuggestedCompetencesForCourse(
            @PathParam("courseId") String courseId) throws Exception;

    /**
     * Get competences linked to (courseId) context.
     * <p/>
     * Returns all the courses linked to a certain competence as a suggestions
     *
     * @param competenceId
     * @return
     */
    String[] getSuggestedCoursesForCompetence(
            @PathParam("competenceId") String competenceId) throws Exception;

    /**
     * The semantic of this interface is that an activity is linked to a competence as template.
     * This way the learner can navigate to the navigate if he/she wants to learn a specific set of competencies
     * @param competenceId should be the plain text string of the competence
     * @param activityId should be the url of the activity
     * @return
     * @throws Exception
     */
    Response createSuggestedActivityForCompetence(@PathParam("competenceId") String competenceId, @PathParam("activityId") String activityId) throws Exception;



    /**
     * Deletes the link between the courseId and the given competence
     * @param competence
     * @param course
     * @return
     */
    Response deleteSuggestedCourseForCompetence(@PathParam("competenceId") String competence, @PathParam("courseId") String course);

    /**
     * Deletes the link between the activity and the given competence
     * @param competenceId
     * @param activityId
     * @return
     */
    Response deleteSuggestedActivityForCompetence(@PathParam("competenceId") String competenceId, @PathParam("activityId") String activityId);
}
