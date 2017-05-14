package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.MenuItemCategory;
import com.example.demo.web.dto.MenuItemCategoryDTO;


@Component
public class MenuItemCategoryToMenuItemCategoryDTO implements Converter<MenuItemCategory, MenuItemCategoryDTO> {


	@Override
	public MenuItemCategoryDTO convert(MenuItemCategory menuItemCategory) {
		MenuItemCategoryDTO dto = new MenuItemCategoryDTO();
		
		dto.setId(menuItemCategory.getId());
		dto.setName(menuItemCategory.getName());
		return dto;
	}
	
	public List<MenuItemCategoryDTO> convert(List<MenuItemCategory> menuItemCategoryList){
		List<MenuItemCategoryDTO> menuItemCategoryDTOList = new ArrayList<>();
		
		for(MenuItemCategory menuItemCategory : menuItemCategoryList){
			menuItemCategoryDTOList.add(convert(menuItemCategory));
		}
		
		return menuItemCategoryDTOList;
	}
}
