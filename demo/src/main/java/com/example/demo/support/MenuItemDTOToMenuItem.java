package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.MenuItem;
import com.example.demo.service.MenuItemService;
import com.example.demo.web.dto.MenuItemDTO;

import com.example.demo.support.MenuItemCategoryDTOToMenuItemCategory;
import com.example.demo.support.SportShopDTOToSportShop;

@Component
public class MenuItemDTOToMenuItem
	implements Converter<MenuItemDTO, MenuItem> {
	
	@Autowired
	private MenuItemCategoryDTOToMenuItemCategory toMenuItemCategory;
	@Autowired
	private SportShopDTOToSportShop toSportShop;
	
	@Autowired
	MenuItemService menuItemService;

	@Override
	public MenuItem convert(MenuItemDTO dto) {
		MenuItem menuItem = new MenuItem();
		
		if(dto.getId()!=null){
			menuItem = menuItemService.findOne(dto.getId());
			
			if(menuItem == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant menuItem");
			}
		}
		
		menuItem.setId(dto.getId());
		
	    menuItem.setName(dto.getName());
	    menuItem.setDescription(dto.getDescription());
	    menuItem.setPrice(dto.getPrice());
		menuItem.setMenuItemCategory(toMenuItemCategory.convert(dto.getMenuItemCategory()));
		menuItem.setSportShop(toSportShop.convert(dto.getSportShop()));
		
		
		return menuItem;
	}
	
	public List<MenuItem> convert (List<MenuItemDTO> menuItemDTOList){
		List<MenuItem> menuItemList = new ArrayList<>();
		
		for(MenuItemDTO dto : menuItemDTOList){
			menuItemList.add(convert(dto));
		}
		
		return menuItemList;
	}

}