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


@Table(name="admin")
@Entity
public class Admin extends AbstractBaseEntity { 
 
	@Column(name="username"
	)
    private String username;
        
	@Column(name="password"
	)
    private String password;
        

	public Admin() {}

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




