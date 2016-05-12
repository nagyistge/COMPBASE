package uzuzjmd.competence.api;

import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Created by dehne on 15.04.2016.
 */
@Path("/api1")
public interface CompetenceApi {

    /**
     * returns either a list of string or a tree representation depending on the value of "asTree"
     *
     * @param selectedCatchwords
     * @param selectedOperators
     * @param textFilter
     * @param rootCompetence
     * @param course
     * @param asTree
     * @return
     */
    @Path("/competences")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getCompetences(@QueryParam(value = "selectedCatchwords") java.util.List<String> selectedCatchwords,
                            @QueryParam(value = "selectedOperators") java.util.List<String> selectedOperators,
                            @QueryParam("textFilter") String textFilter, @QueryParam("rootCompetence") String rootCompetence, @QueryParam("course") String course, @QueryParam("asTree") Boolean asTree, @QueryParam("userId") String userId);

    /**
     * Add a competence to the model with the id (the competence string) and competence meta data as payload.
     *
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/competences/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data);

    @Path("/competences/{competenceId}")
    @DELETE
    Response deleteCompetence(@PathParam("competenceId") String competenceId) throws Exception;

    /**
     * Delete a competence with competenceId from the database using DELETE
     *
     * if the user is added, the competence will NOT be deleted but hidden for the given user
     * @param competenceId
     * @return
     * @throws Exception
     */
    @Path("/competences/{competenceId}")
    @DELETE
    Response deleteCompetence(@PathParam("competenceId") String competenceId,  String userId) throws Exception;

    /**
     * Delete a acompetence with competenceID from the database using POST
     * @param competenceId
     * @return
     * @throws Exception
     */
    @Path("/competences/{competenceId}/delete")
    @POST
    Response deleteCompetenceLegacy(@PathParam("competenceId") String competenceId) throws Exception;

    /**
     * add competence with competenceId to the database using POST
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/competences/{competenceId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCompetenceLegacy(@PathParam("competenceId") String competenceId, CompetenceData data);

    /**
     * update the competence hierarchy with the changeSet
     * @param changes
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/competences/hierarchy/update")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response updateHierarchy(HierarchyChangeSet changes);


    /**
     * create a comment using POST
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/competences/{competenceId}/comments")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addComment(@PathParam("competenceId") String competenceId, CommentData data) throws Exception;


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @param commentId
     * @return
     */
    @Path("/competences/{competenceId}/comments/{commentId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getComment(@PathParam("competenceId") String competenceId, @PathParam("commentId") String commentId) throws Exception;


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @param commentId
     * @return
     */
    @Path("/competences/{competenceId}/comments/{commentId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response deleteComment(@PathParam("competenceId") String competenceId, @PathParam("commentId") String commentId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/competences/{competenceId}/comments")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    CommentData[] getComments(@PathParam("competenceId") String competenceId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/competences/{competenceId}/verify")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Boolean verifyCompetence(@PathParam("competenceId") String competenceId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/competences/{competenceId}/similar")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    ArrayList<String> similarCompetences(@PathParam("competenceId") String competenceId) throws Exception;
}
