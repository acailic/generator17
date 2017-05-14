package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.Seller;
import com.example.demo.service.SellerService;
import com.example.demo.support.SellerDTOToSeller;
import com.example.demo.support.SellerToSellerDTO;
import com.example.demo.web.dto.SellerDTO;

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
@RequestMapping(value = "/api/sellerList")
public class ApiSellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private SellerToSellerDTO toDTO;

	@Autowired
	private SellerDTOToSeller toSeller;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SellerDTO>> getSellerList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<Seller> sellerList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<Seller> sellerListPage;
		
		if(page != null) {
			sellerListPage = sellerService.findAll(page);
			totalItems = sellerService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			sellerList = sellerListPage.getContent();
		}
		else {
			sellerList = sellerService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(sellerList), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<SellerDTO> getSeller(@PathVariable Long id) {
		Seller seller = sellerService.findOne(id);
		if (seller == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(seller), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SellerDTO> delete(@PathVariable Long id) {
		Seller deleted = sellerService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SellerDTO> add(@RequestBody @Valid SellerDTO newSeller) {

		Seller savedSeller = sellerService.save(toSeller
				.convert(newSeller));

		return new ResponseEntity<>(toDTO.convert(savedSeller), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SellerDTO> edit(@RequestBody @Valid SellerDTO seller,
			@PathVariable Long id) {

		if (id != seller.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Seller persisted = sellerService.save(toSeller.convert(seller));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
