package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SportShopDTO extends AbstractBaseDTO { 

	@NotNull
    @Size(min=0, max=35)
    private String name;
    @Size(min=0, max=40)
    private String streetName;
    @Min(1)
    @Max(900)	
    private int streetNumber;
    @Min(-180)
    @Max(180)	
    private double gpsLatitude;
    private SportShopBrandDTO sportShopBrand;	
    private CityDTO city;	
    @Min(-180)
    @Max(180)	
    private double gpsLongitude;

	public SportShopDTO() {}

	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}	
	public String getStreetName(){
    	return streetName;
  	}
  
  	public void setStreetName(String streetName){
       	this.streetName = streetName;	
	}	
	public int getStreetNumber(){
    	return streetNumber;
  	}
  
  	public void setStreetNumber(int streetNumber){
       	this.streetNumber = streetNumber;	
	}	
	public double getGpsLatitude(){
    	return gpsLatitude;
  	}
  
  	public void setGpsLatitude(double gpsLatitude){
       	this.gpsLatitude = gpsLatitude;	
	}	
	public SportShopBrandDTO getSportShopBrand(){
    	return sportShopBrand;
  	}
  
  	public void setSportShopBrand(SportShopBrandDTO sportShopBrand){
       	this.sportShopBrand = sportShopBrand;	
	}	
	public CityDTO getCity(){
    	return city;
  	}
  
  	public void setCity(CityDTO city){
       	this.city = city;	
	}	
	public double getGpsLongitude(){
    	return gpsLongitude;
  	}
  
  	public void setGpsLongitude(double gpsLongitude){
       	this.gpsLongitude = gpsLongitude;	
	}	

}
