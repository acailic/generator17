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


@Table(name="seller")
@Entity
public class Seller extends AbstractBaseEntity { 
 
	@Column(name="username"
	, unique=true)
    private String username;
        
	@Column(name="password"
	)
    private String password;
        
	@Column(name="firstname"
	)
    private String firstName;
        
	@Column(name="lastname"
	)
    private String lastName;
        
    @OneToMany(mappedBy="seller",cascade=CascadeType.REMOVE)
	private List<SportShopBrand> sportShopBrandsList = new ArrayList<SportShopBrand>();
        
	@Column(name="email"
	)
    private String email;
        

	public Seller() {}

  	public String getUsername(){
    	return username;
  	}
  
  	public void setUsername(String username){
       	this.username = username;	
	}
      
  	public String getPassword(){
    	return password;
  	}
  
  	public void setPassword(String password){
       	this.password = password;	
	}
      
  	public String getFirstName(){
    	return firstName;
  	}
  
  	public void setFirstName(String firstName){
       	this.firstName = firstName;	
	}
      
  	public String getLastName(){
    	return lastName;
  	}
  
  	public void setLastName(String lastName){
       	this.lastName = lastName;	
	}
      
   	public void addSportShopBrands(SportShopBrand sportShopBrands){
		this.sportShopBrandsList.add(sportShopBrands);
		
		if(sportShopBrands.getSeller() != this){
			sportShopBrands.setSeller(this);
		}
	}
	
	public void removeSportShopBrands(SportShopBrand sportShopBrands){
		sportShopBrands.setSeller(null);
		sportShopBrandsList.remove(sportShopBrands);
	}
	
  	public String getEmail(){
    	return email;
  	}
  
  	public void setEmail(String email){
       	this.email = email;	
	}
      

}




