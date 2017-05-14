package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdminDTO extends AbstractBaseDTO { 

    private String username;
    private String password;

	public AdminDTO() {}

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

}
