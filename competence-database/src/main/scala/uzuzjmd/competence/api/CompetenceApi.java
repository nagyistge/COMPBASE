package uzuzjmd.competence.api;

import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by dehne on 15.04.2016.
 */
@Path("/api1/competences")
public interface CompetenceApi {
    /**
     * GET all competences that match the filter params.
     *
     * @param selectedCatchwords
     * @param selectedOperators
     * @param textFilter
     * @param rootCompetence
     * @param course
     * @param asTree
     * @return
     */
    @Path("/")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getCompetences(@QueryParam(value = "selectedCatchwords") java.util.List<String> selectedCatchwords,
                            @QueryParam(value = "selectedOperators") java.util.List<String> selectedOperators,
                            @QueryParam("textFilter") String textFilter, @QueryParam("rootCompetence") String rootCompetence, @QueryParam("course") String course, @QueryParam("asTree") Boolean asTree);

    /**
     * Add a competence to the model with the id (the competence string) and competence meta data as payload.
     *
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data);

    /**
     * Delete a competence with competenceId from the database using DELETE
     * @param competenceId
     * @return
     * @throws Exception
     */
    @Path("/{competenceId}")
    @DELETE
    Response deleteCompetence(@PathParam("competenceId") String competenceId) throws Exception;

    /**
     * Delete a acompetence with competenceID from the database using POST
     * @param competenceId
     * @return
     * @throws Exception
     */
    @Path("/{competenceId}/delete")
    @POST
    Response deleteCompetenceLegacy(@PathParam("competenceId") String competenceId) throws Exception;

    /**
     * add competence with competenceId to the database using POST
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/{competenceId}/create")
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
    @Path("/hierarchy/update")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response updateHierarchy(HierarchyChangeSet changes);


    /**
     * create a comment using POST
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/{competenceId}/comments")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addComment(@PathParam("competenceId") String competenceId, CommentData data);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @param commentId
     * @return
     */
    @Path("/{competenceId}/comments/{commentId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    CommentData getComment(@PathParam("competenceId") String competenceId, @PathParam("commentId") String commentId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @param commentId
     * @return
     */
    @Path("/{competenceId}/comments/{commentId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response deleteComment(@PathParam("competenceId") String competenceId, @PathParam("commentId") String commentId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/{competenceId}/comments")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    CommentData[] getComments(@PathParam("competenceId") String competenceId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/{competenceId}/verify")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Boolean verifyCompetence(@PathParam("competenceId") String competenceId);


    /**
     * get all the comment for the competence with the id given
     * @param competenceId
     * @return
     */
    @Path("/{competenceId}/similar")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    List<String> similarCompetences(@PathParam("competenceId") String competenceId);
}
