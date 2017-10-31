/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.crudsrv.gradebook.xzhan320.netbeans;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Path("Gradebook")
public class GradebookResource {
    
    private static final Logger LOG = LoggerFactory.getLogger(GradebookResource.class);
    
    private static List<Gradebook> gradebookList;
    
    private static int gradebookId;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GradebookResource
     */
    public GradebookResource() {
        LOG.info("Creating a Gradebook Resource");
    }

    /**
     * POST method for creating an instance of ScratchResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createResource(String content) {
        Gradebook gradebook = null;
        LOG.info("Creating the instance Gradebook Resource: ");
        LOG.debug("POST request");
        LOG.debug("Request Content = {}", content);
        
        Response response;
        
        if (gradebookList == null){
            gradebookList = new ArrayList<>();
            LOG.debug("Attempting to create a Gradebook Resource and setting it to {}", content);
            
            try {
                gradebook = (Gradebook) Converter.convertFromXmlToObject(content, Gradebook.class);
                
                LOG.debug("The XML {} was converted to the object {}", content, gradebook);
                
                LOG.info("Creating a {} {} Status Response", Response.Status.CREATED.getStatusCode(), Response.Status.CREATED.getReasonPhrase());
                
                // Id for first-time newly created resource
                Random randomGenerator = new Random(1234567890);
                gradebookId = Math.abs(randomGenerator.nextInt(900)+100);
                gradebook.setId(gradebookId);
                
                String xmlString = Converter.convertFromObjectToXml(gradebook, Gradebook.class);
                
                URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(gradebookId));
                
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(xmlString).build();
                
                gradebookList.add(gradebook);
                gradebookId++;
            } catch (JAXBException e) {
                LOG.info("Creating a {} {} Status Response", Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase());
                LOG.debug("XML is {} is incompatible with Gradebook Resource", content);
                
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
                LOG.debug("Catch All exception");
                
                LOG.info("Creating a {} {} Status Response", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
                
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
        }
        else{
            
            LOG.debug("Attempting to create a Gradebook Resource and setting it to {}", content);
            
            try{
                gradebook = (Gradebook) Converter.convertFromXmlToObject(content, Gradebook.class);
                
                LOG.debug("The XML {} was converted to the object {}", content, gradebook);
                
                LOG.info("Creating a {} {} Status Response", Response.Status.CREATED.getStatusCode(), Response.Status.CREATED.getReasonPhrase());
                
                // Collision Inspection
                Gradebook curr_grdbook;
                for(int index = 0; index < gradebookList.size(); index++)
                {
                    curr_grdbook = gradebookList.get(index);
                    if(curr_grdbook.getStudentId().equals(gradebook.getStudentId()) && curr_grdbook.getGradingItem().equals(gradebook.getGradingItem()))
                    {
                        // Collision Found
                        LOG.info("Creating a {} {} Status Response", Response.Status.CONFLICT.getStatusCode(), Response.Status.CONFLICT.getReasonPhrase());
                        LOG.debug("Cannot create Gradebook Resource as values is already set to {}", curr_grdbook);
                        
                        response = Response.status(Response.Status.CONFLICT).entity(content).build();
                        
                        LOG.debug("Generated response {}", response);
                        return response;
                    }
                }
                
                // No collision
                gradebook.setId(gradebookId);
                
                String xmlString = Converter.convertFromObjectToXml(gradebook, Gradebook.class);
                
                URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(gradebookId));
                
                response = Response.status(Response.Status.CREATED).location(locationURI).entity(xmlString).build();
                
                gradebookList.add(gradebook);
                gradebookId++;
            } catch (JAXBException e) {
                LOG.info("Creating a {} {} Status Response", Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase());
                LOG.debug("XML is {} is incompatible with Gradebook Resource", content);
                
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
                LOG.debug("Catch All exception");
                
                LOG.info("Creating a {} {} Status Response", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
                
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
        }
        
        LOG.debug("Generated response {}", response);
        
        return response;
    }

    /**
     * Retrieves representation of an instance of com.myasu.cse446.sample.crud.restws.AppointmentResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getResource(@PathParam("id") String id) {
        LOG.info("Retrieving the Gradebook Resource: ");
        LOG.debug("GET request");
        LOG.debug("PathParam id = {}", id);
        
        Response response;
        
        if (gradebookList == null){
            LOG.info("Creating a {} {} Status Response", Response.Status.GONE.getStatusCode(), Response.Status.GONE.getReasonPhrase());
            LOG.debug("No Gradebook Resource to return");
            
            response = Response.status(Response.Status.GONE).entity("No Gradebook Resource to return").build();
        } 
        else {
            Gradebook curr_grdbook;
            //LOG.debug("gradebook.getId() = {}", gradebook.getId());
            for(int index = 0; index < gradebookList.size(); index++)
            {
                curr_grdbook = gradebookList.get(index);
                if (curr_grdbook.getId() == Integer.parseInt(id)){
                    LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
                    LOG.debug("Retrieving the Appointment Resource {}", curr_grdbook);

                    String xmlString = Converter.convertFromObjectToXml(curr_grdbook, Gradebook.class);

                    response = Response.status(Response.Status.OK).entity(xmlString).build();
                    
                    LOG.debug("Returning the value {}", response);
                    
                    return response;
                }
            }
            LOG.info("Creating a {} {} Status Response", Response.Status.NOT_FOUND.getStatusCode(), Response.Status.NOT_FOUND.getReasonPhrase());
            //LOG.debug("Retrieving the Gradebook Resource {}", curr_grdbook);
            
            response = Response.status(Response.Status.NOT_FOUND).entity("No Appointment Resource to return").build();
        }        
        
        LOG.debug("Returning the value {}", response);
        
        return response;
    }

    /**
     * PUT method for updating an instance of ScratchResource
     * @param id
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateResource(@PathParam("id") String id, String content) {
        Gradebook gradebook;
        LOG.info("Updating the Gradebook Resource {} with {}", id, content);
        LOG.debug("PUT request");
        LOG.debug("PathParam id = {}", id);
        LOG.debug("Request Content = {}", content);
        
        Response response;
        
        if (gradebookList != null){
            LOG.debug("Attempting to update the Gradebook Resource ...");
            
            Gradebook curr_grdbook;
            try {
                gradebook = (Gradebook) Converter.convertFromXmlToObject(content, Gradebook.class);
                
                for(int index = 0; index < gradebookList.size(); index++)
                {
                    curr_grdbook = gradebookList.get(index);
                    
                    if(curr_grdbook.getId() == gradebook.getId())
                    {
                        curr_grdbook.setStudentId(gradebook.getStudentId());
                        curr_grdbook.setGradingItem(gradebook.getGradingItem());
                        curr_grdbook.setGrade(gradebook.getGrade());
                        curr_grdbook.setGradingComment(gradebook.getGradingComment());
                        
                        LOG.debug("The XML {} was converted to the object {}", content, gradebook);         
                        LOG.debug("Updated Gradebook Resource to {}", gradebook);

                        LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());

                        String xmlString = Converter.convertFromObjectToXml(gradebook, Gradebook.class);

                        response = Response.status(Response.Status.OK).entity(content).build();
                        
                        LOG.debug("Generated response {}", response);
                        
                        return response;
                    }
                }
                
                LOG.info("Creating a {} {} Status Response", Response.Status.CONFLICT.getStatusCode(), Response.Status.CONFLICT.getReasonPhrase());
                LOG.debug("Cannot update Gradebook Resource {} as it has not yet been created", content);

                response = Response.status(Response.Status.CONFLICT).entity(content).build();
            } catch (JAXBException e) {
                LOG.info("Creating a {} {} Status Response", Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase());
                LOG.debug("XML is {} is incompatible with Gradebook Resource", content);
                
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
                LOG.debug("Catch All exception");
                
                LOG.info("Creating a {} {} Status Response", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
                
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
        } else {
            LOG.info("Creating a {} {} Status Response", Response.Status.CONFLICT.getStatusCode(), Response.Status.CONFLICT.getReasonPhrase());
            LOG.debug("Cannot update Gradebook Resource {} as it has not yet been created", content);
                      
            response = Response.status(Response.Status.CONFLICT).entity(content).build();
        }

        LOG.debug("Generated response {}", response);
        
        return response;
    }

    /**
     * Retrieves representation of an instance of com.myasu.cse446.sample.crud.restws.AppointmentResource
     * @param id
     * @return an instance of java.lang.String
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteResource(@PathParam("id") String id) {
        LOG.info("Removing the Gradebook Resource: ");
        LOG.debug("DELETE request");
        LOG.debug("PathParam id = {}", id);
        
        Response response;
        
        if (gradebookList == null || gradebookList.isEmpty()){
            LOG.info("Creating a {} {} Status Response", Response.Status.GONE.getStatusCode(), Response.Status.GONE.getReasonPhrase());
            LOG.debug("No Gradebook Resource to delete");
            
            response = Response.status(Response.Status.GONE).build();
        }
        else {
            Gradebook curr_grdbook;
            
            for(int index = 0; index < gradebookList.size(); index++)
            {
                curr_grdbook = gradebookList.get(index);
                
                if (curr_grdbook.getId() == Integer.parseInt(id))
                {
                    LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
                    LOG.debug("Deleting the Gradebook Resource {}", curr_grdbook);

                    gradebookList.remove(index);

                    response = Response.status(Response.Status.NO_CONTENT).build();

                    LOG.debug("Generated response {}", response);

                    return response;
                } 
            }
            
            LOG.info("Creating a {} {} Status Response", Response.Status.NOT_FOUND.getStatusCode(), Response.Status.NOT_FOUND.getReasonPhrase());
            //LOG.debug("Retrieving the Gradebook Resource {}", curr_grdbook);
            
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        
        LOG.debug("Generated response {}", response);
        
        return response;
    }
}
