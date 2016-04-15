package uzuzjmd.competence.api;

import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 15.04.2016.
 */
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
    @Path("/competences")
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
    @Path("/competences/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data);

    /**
     * Delete a competence with competenceId from the database using DELETE
     * @param competenceId
     * @return
     * @throws Exception
     */
    @Path("/competences/{competenceId}")
    @DELETE
    Response deleteCompetence(@PathParam("competenceId") String competenceId) throws Exception;

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
}
