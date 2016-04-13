package uzuzjmd.competence.service.rest;

import com.google.common.collect.Lists;
import uzuzjmd.competence.mapper.rest.write.Comment2Ont;
import uzuzjmd.competence.mapper.rest.write.Evidence2Ont;
import uzuzjmd.competence.mapper.rest.write.HandleLinkValidationInOnt;
import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.EvidenceData;
import uzuzjmd.competence.service.rest.dto.LinkValidationData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1/evidences")
public class EvidenceApiImpl {
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
    public Response linkCompetencesToUser(@PathParam("evidenceURL") String evidenceURL, EvidenceData data) {
        return createEvidenceLink(evidenceURL, data);
    }


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
    public Response linkCompetencesToUser2(@PathParam("evidenceId") String evidenceId, EvidenceData data) {
        return createEvidenceLink(evidenceId, data);
    }

    private Response createEvidenceLink(@PathParam("evidenceURL") String evidenceURL, EvidenceData data) {
        if (data.getEvidences() != null) {
            data.getEvidences().add(evidenceURL);
        } else {
            data.setEvidences(Lists.newArrayList(evidenceURL));
        }
        Evidence2Ont.writeLinkToDatabase(data);
        return Response.ok(
                "competences linked to evidences").build();
    }

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
    public Response commentCompetence(@PathParam("evidenceId")String evidenceId, CommentData commentData) {
        commentData.setLinkId(evidenceId);
        Comment2Ont.convert(commentData);
        return Response.ok("link commented").build();
    }

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
    public Response validateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = true;
        return handleLinkValidation(evidenceId, isValid);
    }

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
    public Response inValidateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = false;
        return handleLinkValidation(evidenceId, isValid);
    }

    private Response handleLinkValidation(String linkId,
                                          Boolean isValid) {
        HandleLinkValidationInOnt
                .convert(new LinkValidationData(linkId,
                        isValid));
        return Response.ok("link updated").build();
    }
}
