package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.mapper.rest.write.HierarchieChangesToOnt;
import uzuzjmd.competence.shared.dto.HierarchyChangeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by dehne on 11.04.2016.
 */

@Path("/nr")
public class NonResourceApiImpl {

    /**
     * updates the competence hierarchy
     *
     * @param changes of type HierarchieChangeObject @see updateHierarchie2
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/competence/hierarchy/update")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateHierarchy(HierarchyChangeSet changes) {
        HierarchieChangesToOnt.convert(changes);
        return Response.ok("updated taxonomy").build();
    }
}
