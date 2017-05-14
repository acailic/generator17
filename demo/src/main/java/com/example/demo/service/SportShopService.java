package com.example.demo.service;

import com.example.demo.model.SportShop;
import java.util.List;

public interface SportShopService extends CrudService<SportShop> {
	
	List<SportShop> findByName(String name);
	
	List<SportShop> findBySportShopBrandId(Long id);
	
	List<SportShop> findByCityId(Long id);
	
}
