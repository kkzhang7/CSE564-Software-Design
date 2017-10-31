/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxsrv.foodmenu.xzhan320.netbeans;

import com.myasu.poxsrv.foodmenu.xzhan320.interpretation.FoodItemData;

/**
 *
 * @author xzhan
 */
public interface InterfaceFoodItemSpace {
    public int addFoodItem(FoodItemData foodItem);
    public FoodItemData getFoodItem(int foodItemId);
    public int foodItemExists(FoodItemData foodItem);
}
