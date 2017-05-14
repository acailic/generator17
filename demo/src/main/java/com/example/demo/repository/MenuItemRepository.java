package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.MenuItem;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	List<MenuItem> findByMenuItemCategoryId(Long id);
	
	List<MenuItem> findBySportShopId(Long id);
	

}
