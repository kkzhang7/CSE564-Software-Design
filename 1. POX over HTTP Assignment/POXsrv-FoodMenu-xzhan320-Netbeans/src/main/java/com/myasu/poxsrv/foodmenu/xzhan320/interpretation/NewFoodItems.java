/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxsrv.foodmenu.xzhan320.interpretation;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NewFoodItems")
public class NewFoodItems extends RequestMessage{
    List<FoodItemData> foodItem;
    // List<> method
    public List<FoodItemData> getFoodItem() {
        return foodItem;
    }
    
    @XmlElement(name = "FoodItem")
    public void setFoodItem(List<FoodItemData> foodItem) {
        this.foodItem = foodItem;
    }	
}
