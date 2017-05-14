package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import com.example.demo.support.CityDTOToCity;
import com.example.demo.support.CityToCityDTO;
import com.example.demo.web.dto.CityDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cityList")
public class ApiCityController {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityToCityDTO toDTO;

	@Autowired
	private CityDTOToCity toCity;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CityDTO>> getCityList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<City> cityList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<City> cityListPage;
		
		if(page != null) {
			cityListPage = cityService.findAll(page);
			totalItems = cityService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			cityList = cityListPage.getContent();
		}
		else {
			cityList = cityService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(cityList), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterByCountry/{id}", method = RequestMethod.GET)
		ResponseEntity<List<CityDTO>> getCityListByCountry(@PathVariable Long id) {

		List<City> cityList;
		
		cityList = cityService.findByCountryId(id);
			
		return new ResponseEntity<>(toDTO.convert(cityList), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CityDTO> getCity(@PathVariable Long id) {
		City city = cityService.findOne(id);
		if (city == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(city), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<CityDTO> delete(@PathVariable Long id) {
		City deleted = cityService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CityDTO> add(@RequestBody @Valid CityDTO newCity) {

		City savedCity = cityService.save(toCity
				.convert(newCity));

		return new ResponseEntity<>(toDTO.convert(savedCity), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<CityDTO> edit(@RequestBody @Valid CityDTO city,
			@PathVariable Long id) {

		if (id != city.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		City persisted = cityService.save(toCity.convert(city));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
