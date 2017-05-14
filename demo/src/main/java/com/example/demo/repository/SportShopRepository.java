package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SportShop;


@Repository
public interface SportShopRepository extends JpaRepository<SportShop, Long> {

	List<SportShop> findByName(String name);
	
	List<SportShop> findBySportShopBrandId(Long id);
	
	List<SportShop> findByCityId(Long id);
	

}
