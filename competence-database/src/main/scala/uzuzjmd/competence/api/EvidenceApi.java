package uzuzjmd.competence.api;

import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.EvidenceData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 15.04.2016.
 */
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
}
