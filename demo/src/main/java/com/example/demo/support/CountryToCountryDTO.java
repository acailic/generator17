package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Country;
import com.example.demo.web.dto.CountryDTO;


@Component
public class CountryToCountryDTO implements Converter<Country, CountryDTO> {


	@Override
	public CountryDTO convert(Country country) {
		CountryDTO dto = new CountryDTO();
		
		dto.setId(country.getId());
		dto.setName(country.getName());
		return dto;
	}
	
	public List<CountryDTO> convert(List<Country> countryList){
		List<CountryDTO> countryDTOList = new ArrayList<>();
		
		for(Country country : countryList){
			countryDTOList.add(convert(country));
		}
		
		return countryDTOList;
	}
}
