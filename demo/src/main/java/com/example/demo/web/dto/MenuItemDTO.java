package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuItemDTO extends AbstractBaseDTO { 

	@NotNull
    @Size(min=0, max=30)
    private String name;
    @Size(min=0, max=255)
    private String description;
    private double price;
    private MenuItemCategoryDTO menuItemCategory;	
    private SportShopDTO sportShop;	

	public MenuItemDTO() {}

	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}	
	public String getDescription(){
    	return description;
  	}
  
  	public void setDescription(String description){
       	this.description = description;	
	}	
	public double getPrice(){
    	return price;
  	}
  
  	public void setPrice(double price){
       	this.price = price;	
	}	
	public MenuItemCategoryDTO getMenuItemCategory(){
    	return menuItemCategory;
  	}
  
  	public void setMenuItemCategory(MenuItemCategoryDTO menuItemCategory){
       	this.menuItemCategory = menuItemCategory;	
	}	
	public SportShopDTO getSportShop(){
    	return sportShop;
  	}
  
  	public void setSportShop(SportShopDTO sportShop){
       	this.sportShop = sportShop;	
	}	

}
