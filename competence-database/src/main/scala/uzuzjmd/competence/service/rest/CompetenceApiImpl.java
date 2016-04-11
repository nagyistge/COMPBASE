package uzuzjmd.competence.service.rest;

import scala.NotImplementedError;
import scala.collection.immutable.List;
import uzuzjmd.competence.service.rest.dto.CompetenceData;
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/api1")
public class CompetenceApiImpl {

    @Path("/competences")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<String> getCompetences(CompetenceFilterData data){
        throw new NotImplementedError();
    }

    @Path("/competences/{competenceId}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addCompetence(@PathParam("competenceId") String competenceId, CompetenceData data) {
        throw new NotImplementedError();
    }

    @Path("/competences/{competenceId}")
    @DELETE
    public Response deleteCompetence(@PathParam("competenceId") String competenceId) {
        throw new NotImplementedError();
    }

    /**
     * Use this legacy method for browsers who do not support http delete
     * @param competenceId
     * @return
     */
    @Path("/competences/{competenceId}/delete")
    @POST
    public Response deleteCompetenceLegacy(@PathParam("competenceId") String competenceId) {
        throw new NotImplementedError();
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
        throw new NotImplementedError();
    }





}
