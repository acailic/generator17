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


@Table(name="menuitemcategory")
@Entity
public class MenuItemCategory extends AbstractBaseEntity { 
 
	@Column(name="name"
	)
    private String name;
        
    @OneToMany(mappedBy="menuItemCategory",cascade=CascadeType.REMOVE)
	private List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
        

	public MenuItemCategory() {}

  	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}
      
   	public void addMenuItems(MenuItem menuItems){
		this.menuItemsList.add(menuItems);
		
		if(menuItems.getMenuItemCategory() != this){
			menuItems.setMenuItemCategory(this);
		}
	}
	
	public void removeMenuItems(MenuItem menuItems){
		menuItems.setMenuItemCategory(null);
		menuItemsList.remove(menuItems);
	}
	

}




