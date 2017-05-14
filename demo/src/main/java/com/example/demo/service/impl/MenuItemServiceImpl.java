package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.service.MenuItemService;


@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Override
	public MenuItem findOne(Long id) {
		return menuItemRepository.findOne(id);
	}

	@Override
	public List<MenuItem> findAll() {
		return menuItemRepository.findAll();
	}
		
	@Override
	public Page<MenuItem> findAll(int page) {
		return menuItemRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public MenuItem save(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}
	
	public MenuItem remove(Long id) {
		MenuItem menuItem = menuItemRepository.findOne(id);
		if(menuItem == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant MenuItem");
		}
		menuItemRepository.delete(menuItem);
		return menuItem;
	}
	
	public List<MenuItem> findByMenuItemCategoryId(Long id) {
		return menuItemRepository.findByMenuItemCategoryId(id);
	}
	
	public List<MenuItem> findBySportShopId(Long id) {
		return menuItemRepository.findBySportShopId(id);
	}
	
	
}
