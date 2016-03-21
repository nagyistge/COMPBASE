package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.crawler.main.SolrApp;
import uzuzjmd.competence.main.EposImporter;
import uzuzjmd.competence.mapper.rest.read.*;
import uzuzjmd.competence.mapper.rest.write.*;
import uzuzjmd.competence.persistence.dao.Competence;
import uzuzjmd.competence.persistence.dao.CourseContext;
import uzuzjmd.competence.persistence.dao.DBInitializer;
import uzuzjmd.competence.persistence.dao.Dao;
import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.service.rest.dto.*;
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder;
import uzuzjmd.competence.shared.StringList;
import uzuzjmd.competence.shared.SuggestedCompetenceGrid;
import uzuzjmd.competence.shared.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Root resource (exposed at "competences" path)
 */
@Path("/crawler")
public class CrawlerServiceRest {

    static private List<Thread> threads = new ArrayList<Thread>();
    static private final int MAXTHREAD = 4;
    /**
     *
     * @param campaign
     * @return
     * @throws Exception
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/start")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response start(
            @QueryParam("campaign") String campaign)
            throws Exception {
        final String campaignOs = campaign;
        if (threads.size() <=  MAXTHREAD) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    SolrApp solrApp = new SolrApp(campaignOs);
                    try {
                        solrApp.excecute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        CrawlerServiceRest.threads.remove(this);
                    }
                }
            });
            threads.add(t);
            t.start();

            return Response.ok("thread started").build();
        } else {
            return Response.ok("too many threads. Plz come back later").build();

        }
    }







}