package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityDTO extends AbstractBaseDTO { 

    @Size(min=2, max=30)
    private String name;
    private CountryDTO country;	

	public CityDTO() {}

	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}	
	public CountryDTO getCountry(){
    	return country;
  	}
  
  	public void setCountry(CountryDTO country){
       	this.country = country;	
	}	

}
