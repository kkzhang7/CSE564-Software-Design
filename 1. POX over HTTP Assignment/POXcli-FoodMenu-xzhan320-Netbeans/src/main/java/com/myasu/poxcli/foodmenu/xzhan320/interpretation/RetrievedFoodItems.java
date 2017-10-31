/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxcli.foodmenu.xzhan320.interpretation;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NewFoodItems")
public class RetrievedFoodItems extends RequestMessage{
    List<FoodItemData> foodItem;
    
    public List<FoodItemData> getFoodItem() {
        return foodItem;
    }
    
    @XmlElement(name = "FoodItem")
    public void setFoodItem(List<FoodItemData> foodItem) {
        this.foodItem = foodItem;
    }
    
    List<Integer> invalidFoodItem;
    
    public List<Integer> getInvalidFoodItem() {
        return invalidFoodItem;
    }
    
    @XmlElementWrapper(name = "InvalidFoodItems")
    @XmlElement(name = "FoodItemId")
    public void setInvalidFoodItem(List<Integer> invalidFoodItem) {
        this.invalidFoodItem = invalidFoodItem;
    }
}
