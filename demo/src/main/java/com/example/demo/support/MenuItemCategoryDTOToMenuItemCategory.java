package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.MenuItemCategory;
import com.example.demo.service.MenuItemCategoryService;
import com.example.demo.web.dto.MenuItemCategoryDTO;


@Component
public class MenuItemCategoryDTOToMenuItemCategory
	implements Converter<MenuItemCategoryDTO, MenuItemCategory> {
	
	
	@Autowired
	MenuItemCategoryService menuItemCategoryService;

	@Override
	public MenuItemCategory convert(MenuItemCategoryDTO dto) {
		MenuItemCategory menuItemCategory = new MenuItemCategory();
		
		if(dto.getId()!=null){
			menuItemCategory = menuItemCategoryService.findOne(dto.getId());
			
			if(menuItemCategory == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant menuItemCategory");
			}
		}
		
		menuItemCategory.setId(dto.getId());
		
	    menuItemCategory.setName(dto.getName());
		
		
		return menuItemCategory;
	}
	
	public List<MenuItemCategory> convert (List<MenuItemCategoryDTO> menuItemCategoryDTOList){
		List<MenuItemCategory> menuItemCategoryList = new ArrayList<>();
		
		for(MenuItemCategoryDTO dto : menuItemCategoryDTOList){
			menuItemCategoryList.add(convert(dto));
		}
		
		return menuItemCategoryList;
	}

}