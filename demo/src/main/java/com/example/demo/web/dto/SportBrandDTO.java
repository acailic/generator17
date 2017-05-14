package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SportBrandDTO extends AbstractBaseDTO { 

	@NotNull
    @Size(min=0, max=35)
    private String name;
    private SellerDTO seller;	

	public SportBrandDTO() {}

	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}	
	public SellerDTO getSeller(){
    	return seller;
  	}
  
  	public void setSeller(SellerDTO seller){
       	this.seller = seller;	
	}	

}
