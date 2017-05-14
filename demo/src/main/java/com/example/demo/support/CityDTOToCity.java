package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import com.example.demo.web.dto.CityDTO;

import com.example.demo.support.CountryDTOToCountry;

@Component
public class CityDTOToCity
	implements Converter<CityDTO, City> {
	
	@Autowired
	private CountryDTOToCountry toCountry;
	
	@Autowired
	CityService cityService;

	@Override
	public City convert(CityDTO dto) {
		City city = new City();
		
		if(dto.getId()!=null){
			city = cityService.findOne(dto.getId());
			
			if(city == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant city");
			}
		}
		
		city.setId(dto.getId());
		
	    city.setName(dto.getName());
		city.setCountry(toCountry.convert(dto.getCountry()));
		
		
		return city;
	}
	
	public List<City> convert (List<CityDTO> cityDTOList){
		List<City> cityList = new ArrayList<>();
		
		for(CityDTO dto : cityDTOList){
			cityList.add(convert(dto));
		}
		
		return cityList;
	}

}