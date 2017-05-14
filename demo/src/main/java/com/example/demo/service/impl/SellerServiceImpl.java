package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Seller;
import com.example.demo.repository.SellerRepository;
import com.example.demo.service.SellerService;


@Service
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public Seller findOne(Long id) {
		return sellerRepository.findOne(id);
	}

	@Override
	public List<Seller> findAll() {
		return sellerRepository.findAll();
	}
		
	@Override
	public Page<Seller> findAll(int page) {
		return sellerRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public Seller save(Seller seller) {
		return sellerRepository.save(seller);
	}
	
	public Seller remove(Long id) {
		Seller seller = sellerRepository.findOne(id);
		if(seller == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant Seller");
		}
		sellerRepository.delete(seller);
		return seller;
	}
	
	
}
