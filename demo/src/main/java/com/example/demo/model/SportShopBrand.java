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


@Table(name="sportshopbrand")
@Entity
public class SportShopBrand extends AbstractBaseEntity { 
 
	@Column(name="name"
	, unique=true)
    private String name;
        
    @OneToMany(mappedBy="sportShopBrand",cascade=CascadeType.REMOVE)
	private List<SportShop> sportShopsList = new ArrayList<SportShop>();
        
	@ManyToOne(fetch=FetchType.LAZY)
    private Seller seller;
        
	@Column(name="admincomment"
	)
    private String adminComment;
        

	public SportShopBrand() {}

  	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}
      
   	public void addSportShops(SportShop sportShops){
		this.sportShopsList.add(sportShops);
		
		if(sportShops.getSportShopBrand() != this){
			sportShops.setSportShopBrand(this);
		}
	}
	
	public void removeSportShops(SportShop sportShops){
		sportShops.setSportShopBrand(null);
		sportShopsList.remove(sportShops);
	}
	
  	public Seller getSeller(){
    	return seller;
  	}
  
  	public void setSeller(Seller seller){
       	this.seller = seller;	
	}
      
  	public String getAdminComment(){
    	return adminComment;
  	}
  
  	public void setAdminComment(String adminComment){
       	this.adminComment = adminComment;	
	}
      

}




