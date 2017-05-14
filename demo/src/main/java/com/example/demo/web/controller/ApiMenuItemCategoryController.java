package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.MenuItemCategory;
import com.example.demo.service.MenuItemCategoryService;
import com.example.demo.support.MenuItemCategoryDTOToMenuItemCategory;
import com.example.demo.support.MenuItemCategoryToMenuItemCategoryDTO;
import com.example.demo.web.dto.MenuItemCategoryDTO;

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
@RequestMapping(value = "/api/menuItemCategoryList")
public class ApiMenuItemCategoryController {

	@Autowired
	private MenuItemCategoryService menuItemCategoryService;

	@Autowired
	private MenuItemCategoryToMenuItemCategoryDTO toDTO;

	@Autowired
	private MenuItemCategoryDTOToMenuItemCategory toMenuItemCategory;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<MenuItemCategoryDTO>> getMenuItemCategoryList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<MenuItemCategory> menuItemCategoryList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<MenuItemCategory> menuItemCategoryListPage;
		
		if(page != null) {
			menuItemCategoryListPage = menuItemCategoryService.findAll(page);
			totalItems = menuItemCategoryService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			menuItemCategoryList = menuItemCategoryListPage.getContent();
		}
		else {
			menuItemCategoryList = menuItemCategoryService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(menuItemCategoryList), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<MenuItemCategoryDTO> getMenuItemCategory(@PathVariable Long id) {
		MenuItemCategory menuItemCategory = menuItemCategoryService.findOne(id);
		if (menuItemCategory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(menuItemCategory), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<MenuItemCategoryDTO> delete(@PathVariable Long id) {
		MenuItemCategory deleted = menuItemCategoryService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MenuItemCategoryDTO> add(@RequestBody @Valid MenuItemCategoryDTO newMenuItemCategory) {

		MenuItemCategory savedMenuItemCategory = menuItemCategoryService.save(toMenuItemCategory
				.convert(newMenuItemCategory));

		return new ResponseEntity<>(toDTO.convert(savedMenuItemCategory), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<MenuItemCategoryDTO> edit(@RequestBody @Valid MenuItemCategoryDTO menuItemCategory,
			@PathVariable Long id) {

		if (id != menuItemCategory.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		MenuItemCategory persisted = menuItemCategoryService.save(toMenuItemCategory.convert(menuItemCategory));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
