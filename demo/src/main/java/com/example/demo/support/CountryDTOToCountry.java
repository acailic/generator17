package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import com.example.demo.web.dto.CountryDTO;


@Component
public class CountryDTOToCountry
	implements Converter<CountryDTO, Country> {
	
	
	@Autowired
	CountryService countryService;

	@Override
	public Country convert(CountryDTO dto) {
		Country country = new Country();
		
		if(dto.getId()!=null){
			country = countryService.findOne(dto.getId());
			
			if(country == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant country");
			}
		}
		
		country.setId(dto.getId());
		
	    country.setName(dto.getName());
		
		
		return country;
	}
	
	public List<Country> convert (List<CountryDTO> countryDTOList){
		List<Country> countryList = new ArrayList<>();
		
		for(CountryDTO dto : countryDTOList){
			countryList.add(convert(dto));
		}
		
		return countryList;
	}

}