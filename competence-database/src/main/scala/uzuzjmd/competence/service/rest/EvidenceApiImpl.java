package uzuzjmd.competence.service.rest;

import com.google.common.collect.Lists;
import scala.collection.immutable.List;
import uzuzjmd.competence.mapper.rest.write.Comment2Ont;
import uzuzjmd.competence.mapper.rest.write.Evidence2Ont;
import uzuzjmd.competence.mapper.rest.write.HandleLinkValidationInOnt;
import uzuzjmd.competence.persistence.dao.Comment;
import uzuzjmd.competence.persistence.dao.EvidenceActivity;
import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.EvidenceData;
import uzuzjmd.competence.service.rest.dto.LinkValidationData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by dehne on 11.04.2016.
 */
@Path("/api1")
public class EvidenceApiImpl implements uzuzjmd.competence.api.EvidenceApi {



    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @GET
    @Path("/evidences")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Boolean dummy() {
        return true;
    }




    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @POST
    @Path("/evidences/{evidenceURL}/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response linkCompetencesToUser(@PathParam("evidenceURL") String evidenceURL, EvidenceData data) {
        java.util.List<String> evidences = data.getEvidences();
        if (evidences == null) {
            throw new WebApplicationException("evidences are not provided");
        }
        if (evidences.isEmpty()) {
            throw new WebApplicationException("evidences are not provided");
        }
        for (String evidence : evidences) {
            if (!evidence.contains(",")) {
                throw new WebApplicationException("evidences need to have the structure 'url,speakingname' or be provided as a hashmap" );
            }
        }
        return createEvidenceLink(evidenceURL, data);
    }


    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PUT
    @Path("/evidences/{evidenceId}")
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    @Path("/evidences/{evidenceId}/comments")
    public Response commentCompetence(@PathParam("evidenceId") String evidenceId, CommentData commentData) {
        commentData.setLinkId(evidenceId);
        Comment2Ont.convert(commentData);
        return Response.ok("link commented").build();
    }

    @Override
    public ArrayList<CommentData> getComments(String evidenceId) {
        EvidenceActivity evidence = new EvidenceActivity(evidenceId);
        try {
            return evidence.getComments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new WebApplicationException("error occurred");
    }

    @Override
    public List<CommentData> deleteComments(String evidenceId) {
        EvidenceActivity evidence = new EvidenceActivity(evidenceId);
        try {
            ArrayList<CommentData> comments = evidence.getComments();
            for (CommentData commentData : comments) {
                Comment comment = new Comment(commentData.getCommentId());
                comment.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    @Path("/evidences/{evidenceId}/validate")
    public Response validateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = true;
        return handleLinkValidation(evidenceId, isValid);
    }

    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    @Path("/evidences/{evidenceId}/invalidate")
    public Response inValidateLink(
            @PathParam("evidenceId") String evidenceId) {
        Boolean isValid = false;
        return handleLinkValidation(evidenceId, isValid);
    }

    @Override
    public CommentData getComment(String evidenceId, String commentId) {
        ArrayList<CommentData> comments = getComments(evidenceId);
        for (CommentData comment : comments) {
            if (comment.getCommentId().equals(commentId)) {
                return comment;
            }
        }
        return null;
    }

    @Override
    public Response deleteComment(String evidenceId, String commentId) {
        Comment comment = new Comment(commentId);
        try {
            comment.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new WebApplicationException("error occurred");
    }

    private Response handleLinkValidation(String linkId,
                                          Boolean isValid) {
        HandleLinkValidationInOnt
                .convert(new LinkValidationData(linkId,
                        isValid));
        return Response.ok("link updated").build();
    }
}
