package com.example.demo.service;

import com.example.demo.model.City;
import java.util.List;

public interface CityService extends CrudService<City> {
	
	List<City> findByName(String name);
	
	List<City> findByCountryId(Long id);
	
}
