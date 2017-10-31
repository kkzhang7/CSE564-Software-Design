/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxsrv.foodmenu.xzhan320.interpretation;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FoodItemAdded")
public class FoodItemAdded extends RequestMessage{
    List<Integer> foodItem;
    
    public List<Integer> getFoodItem() {
        return foodItem;
    }
    
    @XmlElement(name = "FoodItemId")
    public void setFoodItem(List<Integer> foodItem) {
        this.foodItem = foodItem;
    }
}
