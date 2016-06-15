package uzuzjmd.competence.service.rest;

import edu.stanford.nlp.trees.GrammaticalRelation;
import uzuzjmd.competence.comparison.verification.CompetenceVerifierFactory;
import uzuzjmd.competence.mapper.rest.SimilaritiesUpdater;
import uzuzjmd.competence.mapper.rest.read.Ont2CompetenceTree;
import uzuzjmd.competence.mapper.rest.read.Ont2Competences;
import uzuzjmd.competence.mapper.rest.write.Competence2Ont;
import uzuzjmd.competence.mapper.rest.write.HierarchieChangesToOnt;
import uzuzjmd.competence.persistence.dao.Comment;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.User;
import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.service.rest.dto.CommentData;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class CompetenceApiImpl implements uzuzjmd.competence.api.CompetenceApi {


    /**
     * returns either a list of string or a tree representation depending on the value of "asTree"
     *
     * courseId  null should be at least 'university' as default"
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
    public Response getCompetences(@QueryParam(value = "selectedCatchwords") java.util.List<String> selectedCatchwords,
                                   @QueryParam(value = "selectedOperators") java.util.List<String> selectedOperators,
                                   @QueryParam("textFilter") String textFilter, @QueryParam("rootCompetence") String rootCompetence, @QueryParam("courseId") String course, @QueryParam("asTree") Boolean asTree, @QueryParam("userId") String userId, @QueryParam("learningTemplate") String learningTemplate) {
        if (course == null) {
            WebApplicationException ex = new WebApplicationException(new Exception("courseId  null should be at least 'university' as default"));
            return Response.status(400)
                    .entity(ex)
                    .type(MediaType.APPLICATION_JSON).
                            build();
        }

        CompetenceFilterData data = new CompetenceFilterData(selectedCatchwords, selectedOperators, course, null, textFilter, userId, learningTemplate, asTree, rootCompetence);
        if (data != null && data.getResultAsTree() != null && data.getResultAsTree()) {
            java.util.List<CompetenceXMLTree> result = Ont2CompetenceTree.getCompetenceTree(data);
            return Response.status(200).entity(result).build();
        } else {
            java.util.List<String> result = Ont2Competences.convert(data);
            return Response.status(200).entity(result).build();
        }
    }

    @Override
    @Path("/competences/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data) {
        data.setForCompetence(competenceId);
        if (data.getSubCompetences() == null) {
            data.setSubCompetences(new ArrayList<String>());
        }

        if (data.getSuperCompetences() == null) {
            data.setSuperCompetences(new ArrayList<String>());
        }
        String resultMessage = Competence2Ont
                .convert(data);
        return Response.ok(resultMessage).build();
    }

    @Override
    @Path("/competences/{competenceId}")
    @DELETE
    public Response deleteCompetence(@PathParam("competenceId") String competenceId) throws Exception {
        Competence competence = new Competence(competenceId);
        competence.delete();
        return Response.ok("competence deleted").build();
    }


    @Override
    @Path("/competences/{competenceId}/users/{userId}")
    @DELETE
    public Response deleteCompetence(@PathParam("competenceId") String competenceId, @PathParam("userId") String userId) throws Exception {
        Competence competence = new Competence(competenceId);
        if (userId != null) {
            User user = new User(userId);
            if (user.exists()) {
                competence.hideFor(user);
                return Response.ok("competence hidden").build();
            } else {
                throw new WebApplicationException("user does not exist");
            }
        } else {
            throw new WebApplicationException("userId is null");
        }
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     *
     * @param competenceId
     * @return
     */
    @Override
    @Path("/competences/{competenceId}/delete")
    @POST
    public Response deleteCompetenceLegacy(@PathParam("competenceId") String competenceId) throws Exception {
        Competence competence = new Competence(competenceId);
        competence.delete();
        return Response.ok("competence deleted").build();
    }


    /**
     * Use this legacy method for browsers who do not support http put
     *
     * @return
     */

    @Path("/competences/{competenceId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCompetenceLegacy(@PathParam("competenceId") String competenceId, CompetenceData data) {
        data.setForCompetence(data.getForCompetence());
        String resultMessage = Competence2Ont
                .convert(data);
        return Response.ok(resultMessage).build();
    }

    /**
     * updates the competence hierarchy
     *
     * @param changes of type HierarchieChangeObject @see updateHierarchie2
     * @return
     */
    @Override
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/competences/hierarchy/update")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateHierarchy(HierarchyChangeSet changes) {
        HierarchieChangesToOnt.convert(changes);
        return Response.ok("updated taxonomy").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/competences/{competenceId}/comments")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Response addComment(@PathParam("competenceId") String competenceId, CommentData data) throws Exception {
        Competence competence = new Competence(competenceId);
        String context = "university";
        if (data.getCourseContext() != null && !data.getCourseContext().isEmpty()) {
            context = data.getCourseContext();
        }
        CourseContext courseContext = new CourseContext(context);
        courseContext.persist();
        if (data.getText() == null || data.getText().isEmpty()) {
            throw new WebApplicationException(new Exception("text is not valid"));
        }
        User user = new User(data.getUser());
        if (!user.exists()) {
            throw new WebApplicationException(new Exception("user does not exist in database"));
        }
        if (competence.exists()) {
            Comment comment = new Comment(data.getText(), user, System.currentTimeMillis());
            comment.persist();
            comment.createEdgeWith(Edge.CommentOfCourse, courseContext);
            comment.createEdgeWith(Edge.CommentOfCompetence, competence);
        } else {
            throw new WebApplicationException(new Exception("competence does not exist in database"));
        }
        return null;
    }

    @Override
    public Response getComment(String competenceId, String commentId) throws Exception {
        Competence competence = new Competence(competenceId);
        if (!competence.exists()) {
            throw new WebApplicationException(new Exception("competence does not exist in database"));
        }
        if (commentId.isEmpty()) {
            java.util.List<Comment> commentDatas = competence.getAssociatedDaosAsRange(Edge.CommentOfCompetence, Comment.class);
            ArrayList<CommentData> results = new ArrayList<>();
            for (Comment comment : commentDatas) {
                CommentData data = comment.getData();
                data.setCompetenceId(competenceId);
                results.add(data);
            }
            return Response.status(200).entity(commentDatas).build();
        } else {
            Comment comment = new Comment(commentId);
            if (!comment.exists()) {
                throw new WebApplicationException(new Exception("comment does not exist in database"));
            }
            CommentData commentData = new CommentData(comment.getDateCreated(), competenceId, comment.getText(), null, null, null, null);
            return Response.status(200).entity(commentData).build();
        }
    }

    @Override
    public Response deleteComment(String competenceId, String commentId) {
        Comment comment = new Comment(commentId);
        try {
            comment.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok("competence deleted").build();
    }

    @Override
    public CommentData[] getComments(String competenceId) {
        // TODO implement
        throw new WebApplicationException("not implemented");
    }

    @Override
    public Boolean verifyCompetence(String competenceId) {
        return CompetenceVerifierFactory.getSimpleCompetenceVerifier(competenceId, GrammaticalRelation.Language.Any).isCompetence2();
    }


    @GET
    @Path("/competences/semblances/{competenceId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public ArrayList<String> similarCompetences(@PathParam("competenceId") String competenceId) throws Exception {
        Competence competence = new Competence(competenceId);
        SimilaritiesUpdater.updateSimilarCompetencies(competence);
        return competence.getSimilarCompetences();
    }

}
