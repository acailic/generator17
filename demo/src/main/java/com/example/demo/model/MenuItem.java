package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name="menuitem")
@Entity
public class MenuItem extends AbstractBaseEntity { 
 
	@Column(name="name"
	, unique=true)
    private String name;
        
	@Column(name="description"
	)
    private String description;
        
	@Column(name="price"
	)
    private double price;
        
	@ManyToOne(fetch=FetchType.LAZY)
    private MenuItemCategory menuItemCategory;
        
	@ManyToOne(fetch=FetchType.LAZY)
    private SportShop sportShop;
        

	public MenuItem() {}

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
      
  	public MenuItemCategory getMenuItemCategory(){
    	return menuItemCategory;
  	}
  
  	public void setMenuItemCategory(MenuItemCategory menuItemCategory){
       	this.menuItemCategory = menuItemCategory;	
	}
      
  	public SportShop getSportShop(){
    	return sportShop;
  	}
  
  	public void setSportShop(SportShop sportShop){
       	this.sportShop = sportShop;	
	}
      

}




