package uzuzjmd.competence.api;

import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.EvidenceData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by dehne on 15.04.2016.
 */
@Path("/api1/evidences")
public interface EvidenceApi {
    /**
     * Legacy implementation for browsers that do not support put
     * <p/>
     * Creates an evidence as a proof that competences have been acquired by the
     * user by certain activities
     *
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceURL}/create")
    @Produces(MediaType.APPLICATION_JSON)
    Response linkCompetencesToUser(@PathParam("evidenceURL") String evidenceURL, EvidenceData data);

    /**
     * Creates an evidence as a proof that competences have been acquired by the
     * user by certain activities
     *
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{evidenceId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response linkCompetencesToUser2(@PathParam("evidenceId") String evidenceId, EvidenceData data);

    /**
     * Add a comment to an evidence link
     * <p/>
     * The comment is create to the evidence (not the competence)
     * <p/>
     * Have a look at @see linkCompetencesToUser in order to better
     * understand the model of a evidence link.
     *
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/comments")
    Response commentCompetence(@PathParam("evidenceId") String evidenceId, CommentData commentData);

    /**
     * Get all the comments for a  given evidence
     * @param evidenceId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{evidenceId}/comments")
    ArrayList<CommentData> getComments(@PathParam("evidenceId") String evidenceId);


    /**
     * delete the comments to a given evidence
     * @param evidenceId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/{evidenceId}/comments")
    List<CommentData> deleteComments(@PathParam("evidenceId") String evidenceId);




    /**
     * Validate an evidence link.
     * <p/>
     * Have a look at for the nature of the
     * evidence link.
     * <p/>
     * This should only be done by teacher role (which should be checked in the
     * frontend)
     *
     * @param evidenceId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/validate")
    Response validateLink(
            @PathParam("evidenceId") String evidenceId);

    /**
     * Validate an evidence link.
     * <p/>
     * Have a look at for the nature of the
     * evidence link.
     * <p/>
     * This should only be done by teacher role (which should be checked in the
     * frontend)
     *
     * @param evidenceId
     * @return
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/invalidate")
    Response inValidateLink(
            @PathParam("evidenceId") String evidenceId);



    /**
     * get all the comment for the competence with the id given
     * @param evidenceId
     * @param commentId
     * @return
     */
    @Path("/{evidenceId}/comments/{commentId}")
    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    CommentData getComment(@PathParam("evidenceId") String evidenceId, @PathParam("commentId") String commentId);


    /**
     * get all the comment for the competence with the id given
     * @param evidenceId
     * @param commentId
     * @return
     */
    @Path("/{evidenceId}/comments/{commentId}")
    @DELETE
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response deleteComment(@PathParam("evidenceId") String evidenceId, @PathParam("commentId") String commentId);



}
