/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myasu.poxcli.foodmenu.xzhan320.interpretation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xzhan
 */

@XmlRootElement(name = "FoodItem")
public class FoodItemData extends RequestMessage{
    
    private String country;
    private int id;
    private String name;
    private String description;
    private String category;
    private String price;
    
    public FoodItemData() {
    }
    
    public boolean checkEquivalence(FoodItemData foodItem){
        return this.country.equals(foodItem.getCountry()) && this.name.equals(foodItem.getName()) 
                && this.description.trim().equals(foodItem.getDescription().trim()) && this.category.equals(foodItem.getCategory()) 
                && this.price.equals(foodItem.getPrice());
    }
    
    @XmlAttribute
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }
    
    @XmlElement
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    @XmlElement
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    @XmlElement
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    @XmlElement
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    
    @XmlElement
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
}
