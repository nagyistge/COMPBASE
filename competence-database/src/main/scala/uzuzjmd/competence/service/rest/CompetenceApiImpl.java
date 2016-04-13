package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.rest.read.Ont2CompetenceTree;
import uzuzjmd.competence.mapper.rest.read.Ont2Competences;
import uzuzjmd.competence.mapper.rest.write.Competence2Ont;
import uzuzjmd.competence.mapper.rest.write.HierarchieChangesToOnt;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData;
import uzuzjmd.competence.service.rest.dto.CompetenceXMLTree;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class CompetenceApiImpl {

    /**
     * returns either a list of string or a tree representation depending on the value of "asTree"
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
                                   @QueryParam("textFilter") String textFilter, @QueryParam("rootCompetence") String rootCompetence, @QueryParam("course") String course, @QueryParam("asTree") Boolean asTree){

        CompetenceFilterData data = new CompetenceFilterData(selectedCatchwords, selectedOperators, course, null, textFilter, asTree, rootCompetence);
        if (data != null && data.getResultAsTree() != null && data.getResultAsTree()) {
            java.util.List<CompetenceXMLTree> result = Ont2CompetenceTree.getCompetenceTree(data);
            return Response.status(200).entity(result).build();
        } else {
            java.util.List<String> result = Ont2Competences.convert(data);
            return Response.status(200).entity(result).build();
        }
    }

    @Path("/competences/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data) {
        data.setForCompetence(competenceId);
        String resultMessage = Competence2Ont
                .convert(data);
        return Response.ok(resultMessage).build();
    }

    @Path("/competences/{competenceId}")
    @DELETE
    public Response deleteCompetence(@PathParam("competenceId") String competenceId) throws Exception {
        Competence competence = new Competence(competenceId);
        competence.delete();
        return Response.ok("competence deleted").build();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     * @param competenceId
     * @return
     */
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
     * @param competenceId
     * @param data
     * @return
     */
    @Path("/competences/{competenceId}/create")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCompetenceLegacy(@PathParam("competenceId") String competenceId, CompetenceData data) {
        data.setForCompetence(competenceId);
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
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/competences/hierarchy/update")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateHierarchy(HierarchyChangeSet changes) {
        HierarchieChangesToOnt.convert(changes);
        return Response.ok("updated taxonomy").build();
    }





}
