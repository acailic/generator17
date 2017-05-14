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


@Table(name="country")
@Entity
public class Country extends AbstractBaseEntity { 
 
	@Column(name="name"
	)
    private String name;
        
    @OneToMany(mappedBy="country",cascade=CascadeType.REMOVE)
	private List<City> citiesList = new ArrayList<City>();
        

	public Country() {}

  	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}
      
   	public void addCities(City cities){
		this.citiesList.add(cities);
		
		if(cities.getCountry() != this){
			cities.setCountry(this);
		}
	}
	
	public void removeCities(City cities){
		cities.setCountry(null);
		citiesList.remove(cities);
	}
	

}




