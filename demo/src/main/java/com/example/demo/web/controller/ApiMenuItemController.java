package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.MenuItem;
import com.example.demo.service.MenuItemService;
import com.example.demo.support.MenuItemDTOToMenuItem;
import com.example.demo.support.MenuItemToMenuItemDTO;
import com.example.demo.web.dto.MenuItemDTO;

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
@RequestMapping(value = "/api/menuItemList")
public class ApiMenuItemController {

	@Autowired
	private MenuItemService menuItemService;

	@Autowired
	private MenuItemToMenuItemDTO toDTO;

	@Autowired
	private MenuItemDTOToMenuItem toMenuItem;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<MenuItemDTO>> getMenuItemList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<MenuItem> menuItemList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<MenuItem> menuItemListPage;
		
		if(page != null) {
			menuItemListPage = menuItemService.findAll(page);
			totalItems = menuItemService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			menuItemList = menuItemListPage.getContent();
		}
		else {
			menuItemList = menuItemService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(menuItemList), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterByMenuItemCategory/{id}", method = RequestMethod.GET)
		ResponseEntity<List<MenuItemDTO>> getMenuItemListByMenuItemCategory(@PathVariable Long id) {

		List<MenuItem> menuItemList;
		
		menuItemList = menuItemService.findByMenuItemCategoryId(id);
			
		return new ResponseEntity<>(toDTO.convert(menuItemList), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterBySportShop/{id}", method = RequestMethod.GET)
		ResponseEntity<List<MenuItemDTO>> getMenuItemListBySportShop(@PathVariable Long id) {

		List<MenuItem> menuItemList;
		
		menuItemList = menuItemService.findBySportShopId(id);
			
		return new ResponseEntity<>(toDTO.convert(menuItemList), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
		MenuItem menuItem = menuItemService.findOne(id);
		if (menuItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(menuItem), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<MenuItemDTO> delete(@PathVariable Long id) {
		MenuItem deleted = menuItemService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MenuItemDTO> add(@RequestBody @Valid MenuItemDTO newMenuItem) {

		MenuItem savedMenuItem = menuItemService.save(toMenuItem
				.convert(newMenuItem));

		return new ResponseEntity<>(toDTO.convert(savedMenuItem), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<MenuItemDTO> edit(@RequestBody @Valid MenuItemDTO menuItem,
			@PathVariable Long id) {

		if (id != menuItem.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		MenuItem persisted = menuItemService.save(toMenuItem.convert(menuItem));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
