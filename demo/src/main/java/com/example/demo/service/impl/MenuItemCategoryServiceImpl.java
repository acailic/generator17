package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.MenuItemCategory;
import com.example.demo.repository.MenuItemCategoryRepository;
import com.example.demo.service.MenuItemCategoryService;


@Service
@Transactional
public class MenuItemCategoryServiceImpl implements MenuItemCategoryService {

	@Autowired
	private MenuItemCategoryRepository menuItemCategoryRepository;
	
	@Override
	public MenuItemCategory findOne(Long id) {
		return menuItemCategoryRepository.findOne(id);
	}

	@Override
	public List<MenuItemCategory> findAll() {
		return menuItemCategoryRepository.findAll();
	}
		
	@Override
	public Page<MenuItemCategory> findAll(int page) {
		return menuItemCategoryRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public MenuItemCategory save(MenuItemCategory menuItemCategory) {
		return menuItemCategoryRepository.save(menuItemCategory);
	}
	
	public MenuItemCategory remove(Long id) {
		MenuItemCategory menuItemCategory = menuItemCategoryRepository.findOne(id);
		if(menuItemCategory == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant MenuItemCategory");
		}
		menuItemCategoryRepository.delete(menuItemCategory);
		return menuItemCategory;
	}
	
	
}
