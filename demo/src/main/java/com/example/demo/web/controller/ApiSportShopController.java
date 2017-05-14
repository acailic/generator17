package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.SportShop;
import com.example.demo.service.SportShopService;
import com.example.demo.support.SportShopDTOToSportShop;
import com.example.demo.support.SportShopToSportShopDTO;
import com.example.demo.web.dto.SportShopDTO;

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
@RequestMapping(value = "/api/sportShopList")
public class ApiSportShopController {

	@Autowired
	private SportShopService sportShopService;

	@Autowired
	private SportShopToSportShopDTO toDTO;

	@Autowired
	private SportShopDTOToSportShop toSportShop;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SportShopDTO>> getSportShopList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<SportShop> sportShopList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<SportShop> sportShopListPage;
		
		if(page != null) {
			sportShopListPage = sportShopService.findAll(page);
			totalItems = sportShopService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			sportShopList = sportShopListPage.getContent();
		}
		else {
			sportShopList = sportShopService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(sportShopList), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterBySportShopBrand/{id}", method = RequestMethod.GET)
		ResponseEntity<List<SportShopDTO>> getSportShopListBySportShopBrand(@PathVariable Long id) {

		List<SportShop> sportShopList;
		
		sportShopList = sportShopService.findBySportShopBrandId(id);
			
		return new ResponseEntity<>(toDTO.convert(sportShopList), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterByCity/{id}", method = RequestMethod.GET)
		ResponseEntity<List<SportShopDTO>> getSportShopListByCity(@PathVariable Long id) {

		List<SportShop> sportShopList;
		
		sportShopList = sportShopService.findByCityId(id);
			
		return new ResponseEntity<>(toDTO.convert(sportShopList), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<SportShopDTO> getSportShop(@PathVariable Long id) {
		SportShop sportShop = sportShopService.findOne(id);
		if (sportShop == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(sportShop), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SportShopDTO> delete(@PathVariable Long id) {
		SportShop deleted = sportShopService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SportShopDTO> add(@RequestBody @Valid SportShopDTO newSportShop) {

		SportShop savedSportShop = sportShopService.save(toSportShop
				.convert(newSportShop));

		return new ResponseEntity<>(toDTO.convert(savedSportShop), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SportShopDTO> edit(@RequestBody @Valid SportShopDTO sportShop,
			@PathVariable Long id) {

		if (id != sportShop.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		SportShop persisted = sportShopService.save(toSportShop.convert(sportShop));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
