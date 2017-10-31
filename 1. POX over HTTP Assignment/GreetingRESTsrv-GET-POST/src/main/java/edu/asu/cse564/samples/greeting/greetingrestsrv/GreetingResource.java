package edu.asu.cse564.samples.greeting.greetingrestsrv;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class GreetingResource {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingResource.class);
    
    private String greeting;
    
    /**
     * Creates a new instance of HelloworldResource
     */
    public GreetingResource() {
        LOG.info("Creating a GreetingResource Resource");
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @param content
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postTextGreeting(String content) {
        LOG.info("Creating the html greeting message");
        LOG.debug("POST request");
        LOG.debug("Request Content = {}", content);
        
        greeting = "<html><body><h1>" + content + "</body></h1></html>";
        LOG.debug("Greeting text is = {}", greeting);
        
        Response response;
        response = Response.status(Response.Status.CREATED).build();
        
        return response;
    }

    /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public Response getHtmlGreeting() {
        LOG.info("Retrieving the html greeting message");
        LOG.debug("GET request");
        LOG.debug("Greeting message = {}", greeting);
        
        
        Response response;
        response = Response.status(Response.Status.OK).entity(greeting).build();
        
        return response;
    }
}
