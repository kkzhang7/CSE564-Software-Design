/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxcli.foodmenu.xzhan320.netbeans;

import com.myasu.poxcli.foodmenu.xzhan320.interpretation.FoodItemData;
import com.myasu.poxcli.foodmenu.xzhan320.interpretation.NewFoodItems;
import com.myasu.poxcli.foodmenu.xzhan320.interpretation.SelectedFoodItems;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xzhan
 */
public class App {
    // main entry of the client project    
    public static void main( String[] args )
    {
        RESTfulClient restClient = new RESTfulClient();
        XMLformatBuilder xmlBd = new XMLformatBuilder();
        
        List<FoodItemData> foodItemList;
        List<Integer> foodItemIdlist;
        SelectedFoodItems selectedItem;
        String responseMessage;
        String formattedMessage;
        FoodItemData food;
        NewFoodItems newFoodItems;
        
        //Test Sample 1: Retrieving all food item from the given xml file
        System.out.println("Get Test 1: get food items in FoodItemDate.xml with id 119 and 122");
        foodItemIdlist = new ArrayList<>();
        foodItemIdlist.add(119);
        foodItemIdlist.add(122);
        selectedItem = new SelectedFoodItems();
        selectedItem.setFoodItem(foodItemIdlist);
        
        responseMessage = restClient.getSelectedFoodItem(selectedItem);
        formattedMessage = xmlBd.format(responseMessage);
        System.out.println("Response Message is displayed below: ");
        System.out.println(formattedMessage);
        System.out.println("==================================================================");
        
        //Test Sample 2: Retrieving a food Item that does not exist
        System.out.println("Get Test 2: get a food Item that does not exist, say 10000");
        foodItemIdlist = new ArrayList<>();
        foodItemIdlist.add(10000);
        selectedItem = new SelectedFoodItems();
        selectedItem.setFoodItem(foodItemIdlist);
        
        responseMessage = restClient.getSelectedFoodItem(selectedItem);
        formattedMessage = xmlBd.format(responseMessage);
        System.out.println("Response Message is displayed below: ");
        System.out.println(formattedMessage);
        System.out.println("==================================================================");
        
        //Test Sample 3: Adding a new food item
        System.out.println("Add Test 3: add a new food item");
        food = new FoodItemData();
        food.setCountry("CN");
        food.setName("Hot dry noodles");
        food.setDescription("A traditional dish of Wuhan which noodles are firm and chewy, golden and oily, savory and fresh.");
        food.setCategory("Breakfast");
        food.setPrice("3.49");
        newFoodItems = new NewFoodItems();
        foodItemList = new ArrayList<>();
        foodItemList.add(food);
        newFoodItems.setFoodItem(foodItemList);
        
        responseMessage = restClient.addFoodItem(newFoodItems);
        formattedMessage = xmlBd.format(responseMessage);
        System.out.println("Response Message is displayed below: ");
        System.out.println(formattedMessage);
        System.out.println("==================================================================");
        
        //Test Sample 4: Adding the above added food item again
        System.out.println("Add Test 4: add a food item that already exists");
        food = new FoodItemData();
        food.setCountry("CN");
        food.setName("Hot dry noodles");
        food.setDescription("A traditional dish of Wuhan which noodles are firm and chewy, golden and oily, savory and fresh.");
        food.setCategory("Breakfast");
        food.setPrice("3.49");
        newFoodItems = new NewFoodItems();
        foodItemList = new ArrayList<>();
        foodItemList.add(food);
        newFoodItems.setFoodItem(foodItemList);
        
        responseMessage = restClient.addFoodItem(newFoodItems);
        formattedMessage = xmlBd.format(responseMessage);
        System.out.println("Response Message is displayed below: ");
        System.out.println(formattedMessage);
        System.out.println("==================================================================");
        
        //Test Sample 5: Sending an invalid message
        System.out.println("Invalid Add Test 5: add an invalid food item");
        food = new FoodItemData();
        food.setCountry("JP");
        food.setName("Sushi");
        food.setCategory("Lunch");
        food.setPrice("15.99");
        newFoodItems = new NewFoodItems();
        foodItemList = new ArrayList<>();
        foodItemList.add(food);
        newFoodItems.setFoodItem(foodItemList);
        
        responseMessage = restClient.addFoodItem(newFoodItems);
        formattedMessage = xmlBd.format(responseMessage);
        System.out.println("Response Message is displayed below: ");
        System.out.println(formattedMessage);
        System.out.println("==================================================================");
    }
}
