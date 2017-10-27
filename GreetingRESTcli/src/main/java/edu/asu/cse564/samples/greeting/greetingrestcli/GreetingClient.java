/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.samples.greeting.greetingrestcli;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

/**
 * Jersey REST client generated for REST resource:GreetingResource
 * [myresource]<br>
 * USAGE:
 * <pre>
 *        GreetingClient client = new GreetingClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author calliss
 */
public class GreetingClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/GreetingRESTsrv/webapi";

    public GreetingClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("myresource");
    }

    public String getHtml() throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.TEXT_HTML).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
