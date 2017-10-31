/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxsrv.foodmenu.xzhan320.netbeans;

import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.FoodItemData;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author xzhan
 */

@ApplicationScoped
public class FoodItemSpace implements InterfaceFoodItemSpace {
    
    private static final Logger LOGGER = Logger.getLogger(FoodItemSpace.class.getName());
    
    private static List<FoodItemData> foodItemList;
    public static int count_id = 400;
    
    public FoodItemSpace() {
        foodItemList = new ArrayList<>();
        readXMLfile("FoodItemData.xml");
    }
    
    public static void readXMLfile(String fileName){
        try{
            File fXmlFile  = new File(FoodItemSpace.class.getClassLoader().getResource(fileName).toURI());
            
            //Parse the xml file
            // Disclaimer: http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile );
            
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("FoodItem");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    FoodItemData foodItem = new FoodItemData();
                    foodItem.setCountry(eElement.getAttribute("country"));
                    foodItem.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    foodItem.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    foodItem.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    foodItem.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
                    foodItem.setPrice(eElement.getElementsByTagName("price").item(0).getTextContent());
                    
                    // Once done, add foodItem to foodItemList
                    foodItemList.add(foodItem);
                }
            }
        }
        catch(URISyntaxException | ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException ex){
            System.out.println(ex);
        }
    }
    
    /**
     *
     * @param foodItem
     * @return
     */
    @Override
    public int addFoodItem(FoodItemData foodItem) {
        count_id++;
        foodItem.setId(count_id);
        foodItemList.add(foodItem);
        return count_id;
    }
    
    /**
     *
     * @param foodItemId
     * @return
     */
    @Override
    public FoodItemData getFoodItem(int foodItemId) {
        for (FoodItemData food : foodItemList) {
            if (food.getId() == foodItemId)
                return food;
        }
        return null;
    }
    
    /**
     *
     * @param foodItem
     * @return
     */
    @Override
    public int foodItemExists(FoodItemData foodItem) {
        for (FoodItemData food : foodItemList) {
            if (food.checkEquivalence(foodItem)) {
                return food.getId();
            }
        }
        return 0;
    }
}