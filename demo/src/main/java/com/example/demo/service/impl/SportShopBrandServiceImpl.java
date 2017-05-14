package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.SportShopBrand;
import com.example.demo.repository.SportShopBrandRepository;
import com.example.demo.service.SportShopBrandService;


@Service
@Transactional
public class SportShopBrandServiceImpl implements SportShopBrandService {

	@Autowired
	private SportShopBrandRepository sportShopBrandRepository;
	
	@Override
	public SportShopBrand findOne(Long id) {
		return sportShopBrandRepository.findOne(id);
	}

	@Override
	public List<SportShopBrand> findAll() {
		return sportShopBrandRepository.findAll();
	}
		
	@Override
	public Page<SportShopBrand> findAll(int page) {
		return sportShopBrandRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public SportShopBrand save(SportShopBrand sportShopBrand) {
		return sportShopBrandRepository.save(sportShopBrand);
	}
	
	public SportShopBrand remove(Long id) {
		SportShopBrand sportShopBrand = sportShopBrandRepository.findOne(id);
		if(sportShopBrand == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant SportShopBrand");
		}
		sportShopBrandRepository.delete(sportShopBrand);
		return sportShopBrand;
	}
	
	
}
