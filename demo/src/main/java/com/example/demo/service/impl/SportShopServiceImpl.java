package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.SportShop;
import com.example.demo.repository.SportShopRepository;
import com.example.demo.service.SportShopService;


@Service
@Transactional
public class SportShopServiceImpl implements SportShopService {

	@Autowired
	private SportShopRepository sportShopRepository;
	
	@Override
	public SportShop findOne(Long id) {
		return sportShopRepository.findOne(id);
	}

	@Override
	public List<SportShop> findAll() {
		return sportShopRepository.findAll();
	}
		
	@Override
	public Page<SportShop> findAll(int page) {
		return sportShopRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public SportShop save(SportShop sportShop) {
		return sportShopRepository.save(sportShop);
	}
	
	public SportShop remove(Long id) {
		SportShop sportShop = sportShopRepository.findOne(id);
		if(sportShop == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant SportShop");
		}
		sportShopRepository.delete(sportShop);
		return sportShop;
	}
	
	public List<SportShop> findByName(String name) {
		return sportShopRepository.findByName(name);
	}
	
	public List<SportShop> findBySportShopBrandId(Long id) {
		return sportShopRepository.findBySportShopBrandId(id);
	}
	
	public List<SportShop> findByCityId(Long id) {
		return sportShopRepository.findByCityId(id);
	}
	
	
}
