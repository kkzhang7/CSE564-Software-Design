/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.crudcli.gradebook.xzhan320.netbeans;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jersey REST client generated for REST resource:AppointmentResource
 * [Appointment]<br>
 * USAGE:
 * <pre>
 *        Appointment_CRUD_cl client = new Appointment_CRUD_cl();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 */
public class Grading_CRUD_cl {
    private static final Logger LOG = LoggerFactory.getLogger(Grading_CRUD_cl.class);
    
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUDsrv-GradeBook-xzhan320-Netbeans/webresources";

    public Grading_CRUD_cl() {        
        LOG.info("Creating a Grading_CRUD REST client");

        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("Gradebook");
        LOG.debug("webResource = {}", webResource.getURI());
    }

    public ClientResponse createGradebook(Object requestEntity) throws UniformInterfaceException {
        LOG.info("Initiating a Create request");
        LOG.debug("XML String = {}", (String) requestEntity);
        
        return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse deleteGradebook(String id) throws UniformInterfaceException {
        LOG.info("Initiating a Delete request");
        LOG.debug("Id = {}", id);
        
        return webResource.path(id).delete(ClientResponse.class);
    }

    public ClientResponse updateGradebook(Object requestEntity, String id) throws UniformInterfaceException {
        LOG.info("Initiating an Update request");
        LOG.debug("XML String = {}", (String) requestEntity);
        LOG.debug("Id = {}", id);
        
        return webResource.path(id).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }

    public <T> T retrieveGradebook(Class<T> responseType, String id) throws UniformInterfaceException {
        LOG.info("Initiating a Retrieve request");
        LOG.debug("responseType = {}", responseType.getClass());
        LOG.debug("Id = {}", id);
        
        //WebResource resource = webResource;
        //resource = resource.path(id);
        
        return webResource.path(id).accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        LOG.info("Closing the REST Client");
        
        client.destroy();
    }

}
