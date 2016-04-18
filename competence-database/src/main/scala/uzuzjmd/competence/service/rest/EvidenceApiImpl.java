package uzuzjmd.competence.service.rest;

import com.google.common.collect.Lists;
import scala.collection.immutable.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
public class EvidenceApiImpl implements uzuzjmd.competence.api.EvidenceApi {
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceURL}/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response linkCompetencesToUser(@PathParam("evidenceURL") String evidenceURL, EvidenceData data) {
        return createEvidenceLink(evidenceURL, data);
    }


    @Override
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

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/comments")
    public Response commentCompetence(@PathParam("evidenceId") String evidenceId, CommentData commentData) {
        commentData.setLinkId(evidenceId);
        Comment2Ont.convert(commentData);
        return Response.ok("link commented").build();
    }

    @Override
    public List<CommentData> getComments(String evidenceId) {
        throw new NotImplementedException();
    }

    @Override
    public List<CommentData> deleteComments(String evidenceId) {
        throw new NotImplementedException();
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/validate")
    public Response validateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = true;
        return handleLinkValidation(evidenceId, isValid);
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{evidenceId}/invalidate")
    public Response inValidateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = false;
        return handleLinkValidation(evidenceId, isValid);
    }

    @Override
    public Response getComment(String evidenceId, String commentId) {
        throw new WebApplicationException("not implemented");
    }

    @Override
    public Response deleteComment(String evidenceId, String commentId) {
        throw new WebApplicationException("not implemented");
    }

    private Response handleLinkValidation(String linkId,
                                          Boolean isValid) {
        HandleLinkValidationInOnt
                .convert(new LinkValidationData(linkId,
                        isValid));
        return Response.ok("link updated").build();
    }
}
