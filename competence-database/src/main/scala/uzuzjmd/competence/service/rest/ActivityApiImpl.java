package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.EvidenceActivity;
import uzuzjmd.competence.persistence.ontology.Edge;
import datastructures.lists.StringList;
import datastructures.trees.ActivityEntry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by dehne on 15.06.2016.
 */
@Path("/api1")
public class ActivityApiImpl {

    /**
     * 1) create activity
     *
     * @param activity
     * @return
     * @throws Exception
     */
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PUT
    @Path("/activities")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addActivity(ActivityEntry activity) throws Exception {
        new EvidenceActivity(activity.getUrl(), activity.getName()).persist();
        return Response.ok("activity has been created").build();
    }

    /**
     * 2) link activity to competence it is suggested for
     *
     * @param competenceId
     * @param activityUrl
     * @return
     * @throws Exception
     */
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @POST
    @Path("/activities/links/competences/{competenceId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addActivity(@PathParam("competenceId") String competenceId, String activityUrl) throws Exception {
        RecommenderApiImpl impl = new RecommenderApiImpl();
        impl.createSuggestedActivityForCompetence(competenceId, activityUrl);
        return Response.ok("edge has been created").build();
    }

    /**
     * query competences that are linked to this activity
     *
     * @param activityId
     * @return
     * @throws Exception
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/activities/links/competences")
    @Produces(MediaType.APPLICATION_JSON)
    public StringList getCompetencesForSuggestedActivity(@QueryParam("activityId") String activityId) throws Exception {
        EvidenceActivity activity = new EvidenceActivity(activityId);
        if (activity.getAssociatedDaoIdsAsDomain(Edge.SuggestedActivityForCompetence) == null) {
            return new StringList();
        }
        List<String> result = activity.getAssociatedDaoIdsAsDomain(Edge.SuggestedActivityForCompetence);
        return new StringList(result);

    }

    /**
     * query competences that are linked to this activity
     *
     * @return
     * @throws Exception
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    @Path("/activities")
    @Produces(MediaType.APPLICATION_JSON)
    public ActivityEntry[] getActivitiesSuggestedForCompetence(@QueryParam("competenceId") String competenceId) throws Exception {
        Competence competence = new Competence(competenceId);
        if (competence.getAssociatedDaoIdsAsRange(Edge.SuggestedActivityForCompetence) == null) {
            return new ActivityEntry[0];
        }
        List<EvidenceActivity> result = competence.getAssociatedDaosAsRange(Edge.SuggestedActivityForCompetence, EvidenceActivity.class);
        int i = 0;
        ActivityEntry[] activityEntries = new ActivityEntry[result.size()];
        for (EvidenceActivity evidenceActivity : result) {
            activityEntries[i] = evidenceActivity.toActivityEntry();
            i++;
        }
        return activityEntries;
    }


}
