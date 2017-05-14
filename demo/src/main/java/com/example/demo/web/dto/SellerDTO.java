package com.example.demo.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellerDTO extends AbstractBaseDTO { 

	@NotNull
    private String username;
	@NotNull
    @Size(min=5, max=25)
    private String password;
    @Size(min=0, max=25)
    private String firstName;
    @Size(min=0, max=25)
    private String lastName;
    private String email;

	public SellerDTO() {}

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
	public String getEmail(){
    	return email;
  	}
  
  	public void setEmail(String email){
       	this.email = email;	
	}	

}
