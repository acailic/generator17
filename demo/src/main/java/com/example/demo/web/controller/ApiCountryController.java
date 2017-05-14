package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import com.example.demo.support.CountryDTOToCountry;
import com.example.demo.support.CountryToCountryDTO;
import com.example.demo.web.dto.CountryDTO;

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
@RequestMapping(value = "/api/countryList")
public class ApiCountryController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryToCountryDTO toDTO;

	@Autowired
	private CountryDTOToCountry toCountry;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CountryDTO>> getCountryList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<Country> countryList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<Country> countryListPage;
		
		if(page != null) {
			countryListPage = countryService.findAll(page);
			totalItems = countryService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			countryList = countryListPage.getContent();
		}
		else {
			countryList = countryService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(countryList), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CountryDTO> getCountry(@PathVariable Long id) {
		Country country = countryService.findOne(id);
		if (country == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(country), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<CountryDTO> delete(@PathVariable Long id) {
		Country deleted = countryService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CountryDTO> add(@RequestBody @Valid CountryDTO newCountry) {

		Country savedCountry = countryService.save(toCountry
				.convert(newCountry));

		return new ResponseEntity<>(toDTO.convert(savedCountry), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<CountryDTO> edit(@RequestBody @Valid CountryDTO country,
			@PathVariable Long id) {

		if (id != country.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Country persisted = countryService.save(toCountry.convert(country));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
