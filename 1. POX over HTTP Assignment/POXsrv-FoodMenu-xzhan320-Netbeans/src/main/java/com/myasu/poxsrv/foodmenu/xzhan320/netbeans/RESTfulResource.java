/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxsrv.foodmenu.xzhan320.netbeans;

import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.FoodItemAdded;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.InvalidMessage;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.FoodItemData;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.FoodItemExists;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.NewFoodItems;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.RequestMessage;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.RetrievedFoodItems;
import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.SelectedFoodItems;

import java.util.List;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xzhan
 */

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource/")
@ApplicationScoped
public class RESTfulResource {
    
    @Inject
    private InterfaceFoodItemSpace foodItemSpace;
    
    private List<FoodItemData> foodItemList;
    private List<Integer> foodItemId;
    
    @POST
    @Path("AddFoodItem/")
    @Produces(MediaType.TEXT_XML)
    public RequestMessage addFoodItem(NewFoodItems newFoodItems) {
        foodItemList = newFoodItems.getFoodItem();
        List<Integer> newIdList = new ArrayList<>();
        List<Integer> existingIdList = new ArrayList<>();
        
        for(FoodItemData food : foodItemList){
            if(food.getCountry() == null || food.getName() == null || food.getDescription() == null|| 
            food.getCategory() == null || food.getPrice() == null){
                return new InvalidMessage();
            }
            int id = foodItemSpace.foodItemExists(food);
            if(id == 0)
                // food item does not exist, create a new foodItem and add it 
                newIdList.add(foodItemSpace.addFoodItem(food));
            else
                existingIdList.add(id);
        }
        
        if(!newIdList.isEmpty()){
            FoodItemAdded foodItemAdded = new FoodItemAdded();
            foodItemAdded.setFoodItem(newIdList);
            return foodItemAdded;
        }
        else{
            FoodItemExists foodItemExists = new FoodItemExists();
            foodItemExists.setFoodItem(existingIdList);
            return foodItemExists;
        }
    }
    
    @POST
    @Path("GetFoodItem/")
    @Produces(MediaType.TEXT_XML)
    public RequestMessage getFoodItem(SelectedFoodItems selectedFoodItems){
        foodItemId = selectedFoodItems.getFoodItem();
        if(foodItemId == null || foodItemId.isEmpty()){
            return new InvalidMessage();
        }
        List<FoodItemData> foodItems = new ArrayList<>();
        List<Integer> invalidFoodItems = new ArrayList<>();
        
        FoodItemData food;
        for(Integer id : foodItemId){
            food = foodItemSpace.getFoodItem(id);
            if(food != null){
                foodItems.add(food);
            }
            else{
                invalidFoodItems.add(id);
            }
        }
        RetrievedFoodItems retrievedFoodItems = new RetrievedFoodItems();
        retrievedFoodItems.setFoodItem(foodItems);
        retrievedFoodItems.setInvalidFoodItem(invalidFoodItems);
        
        return retrievedFoodItems;
    }
}