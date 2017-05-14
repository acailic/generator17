package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Seller;
import com.example.demo.service.SellerService;
import com.example.demo.web.dto.SellerDTO;


@Component
public class SellerDTOToSeller
	implements Converter<SellerDTO, Seller> {
	
	
	@Autowired
	SellerService sellerService;

	@Override
	public Seller convert(SellerDTO dto) {
		Seller seller = new Seller();
		
		if(dto.getId()!=null){
			seller = sellerService.findOne(dto.getId());
			
			if(seller == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant seller");
			}
		}
		
		seller.setId(dto.getId());
		
	    seller.setUsername(dto.getUsername());
	    seller.setPassword(dto.getPassword());
	    seller.setFirstName(dto.getFirstName());
	    seller.setLastName(dto.getLastName());
	    seller.setEmail(dto.getEmail());
		
		
		return seller;
	}
	
	public List<Seller> convert (List<SellerDTO> sellerDTOList){
		List<Seller> sellerList = new ArrayList<>();
		
		for(SellerDTO dto : sellerDTOList){
			sellerList.add(convert(dto));
		}
		
		return sellerList;
	}

}