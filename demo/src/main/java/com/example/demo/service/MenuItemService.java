package com.example.demo.service;

import com.example.demo.model.MenuItem;
import java.util.List;

public interface MenuItemService extends CrudService<MenuItem> {
	
	List<MenuItem> findByMenuItemCategoryId(Long id);
	
	List<MenuItem> findBySportShopId(Long id);
	
}
