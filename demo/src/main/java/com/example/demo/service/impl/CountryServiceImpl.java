package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;


@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public Country findOne(Long id) {
		return countryRepository.findOne(id);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}
		
	@Override
	public Page<Country> findAll(int page) {
		return countryRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public Country save(Country country) {
		return countryRepository.save(country);
	}
	
	public Country remove(Long id) {
		Country country = countryRepository.findOne(id);
		if(country == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant Country");
		}
		countryRepository.delete(country);
		return country;
	}
	
	
}
