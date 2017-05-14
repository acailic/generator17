package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;


@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public City findOne(Long id) {
		return cityRepository.findOne(id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
		
	@Override
	public Page<City> findAll(int page) {
		return cityRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public City save(City city) {
		return cityRepository.save(city);
	}
	
	public City remove(Long id) {
		City city = cityRepository.findOne(id);
		if(city == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant City");
		}
		cityRepository.delete(city);
		return city;
	}
	
	public List<City> findByName(String name) {
		return cityRepository.findByName(name);
	}
	
	public List<City> findByCountryId(Long id) {
		return cityRepository.findByCountryId(id);
	}
	
	
}
