/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.samples.greeting.greetingrestcli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author calliss
 */
public class App {
         
    public static void main( String[] args )
    {
        
        GreetingClient greetingClient = new GreetingClient();
        
        String responseMessage = greetingClient.getHtml();
        
        System.out.println("The message is ");
        System.out.println(responseMessage);
        
    }
    
}
