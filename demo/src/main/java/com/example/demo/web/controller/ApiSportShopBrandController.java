package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.SportShopBrand;
import com.example.demo.service.SportShopBrandService;
import com.example.demo.support.SportShopBrandDTOToSportShopBrand;
import com.example.demo.support.SportShopBrandToSportShopBrandDTO;
import com.example.demo.web.dto.SportShopBrandDTO;

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
@RequestMapping(value = "/api/sportShopBrandList")
public class ApiSportShopBrandController {

	@Autowired
	private SportShopBrandService sportShopBrandService;

	@Autowired
	private SportShopBrandToSportShopBrandDTO toDTO;

	@Autowired
	private SportShopBrandDTOToSportShopBrand toSportShopBrand;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SportShopBrandDTO>> getSportShopBrandList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<SportShopBrand> sportShopBrandList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<SportShopBrand> sportShopBrandListPage;
		
		if(page != null) {
			sportShopBrandListPage = sportShopBrandService.findAll(page);
			totalItems = sportShopBrandService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			sportShopBrandList = sportShopBrandListPage.getContent();
		}
		else {
			sportShopBrandList = sportShopBrandService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(sportShopBrandList), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<SportShopBrandDTO> getSportShopBrand(@PathVariable Long id) {
		SportShopBrand sportShopBrand = sportShopBrandService.findOne(id);
		if (sportShopBrand == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(sportShopBrand), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SportShopBrandDTO> delete(@PathVariable Long id) {
		SportShopBrand deleted = sportShopBrandService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SportShopBrandDTO> add(@RequestBody @Valid SportShopBrandDTO newSportShopBrand) {

		SportShopBrand savedSportShopBrand = sportShopBrandService.save(toSportShopBrand
				.convert(newSportShopBrand));

		return new ResponseEntity<>(toDTO.convert(savedSportShopBrand), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SportShopBrandDTO> edit(@RequestBody @Valid SportShopBrandDTO sportShopBrand,
			@PathVariable Long id) {

		if (id != sportShopBrand.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		SportShopBrand persisted = sportShopBrandService.save(toSportShopBrand.convert(sportShopBrand));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
