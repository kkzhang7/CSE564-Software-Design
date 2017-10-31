/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxcli.foodmenu.xzhan320.netbeans;

import com.myasu.poxcli.foodmenu.xzhan320.interpretation.NewFoodItems;
import com.myasu.poxcli.foodmenu.xzhan320.interpretation.SelectedFoodItems;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xzhan
 */
public class RESTfulClient {
    /*
    private static final Logger LOG = LoggerFactory.getLogger(RESTfulClient.class.getName());
    
    private Client client;
    
    private static final String XML_NS = "http://cse564.asu.edu/PoxAssignment";
    
    public RESTfulClient(){
        //LOG.info("Creating a RESTfulClient Client");
        
        //ClientConfig config = new DefaultClientConfig();
    }
    */
    
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/POXsrv-FoodMenu-xzhan320-Netbeans/webapi";
    
    public RESTfulClient() {
        client = ClientBuilder.newClient();
    }
    
    public String getSelectedFoodItem(SelectedFoodItems selected) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("myresource/GetFoodItem");
        return resource.request(MediaType.TEXT_XML).post(Entity.entity(selected,MediaType.TEXT_XML), String.class);
    }
    
    public String addFoodItem(NewFoodItems newFoodItems){
        WebTarget resource = client.target(BASE_URI).path("myresource/AddFoodItem");
        return resource.request(MediaType.TEXT_XML).post(Entity.entity(newFoodItems,MediaType.TEXT_XML), String.class);
    }
    
    public void close() {
        client.close();
    }
}
