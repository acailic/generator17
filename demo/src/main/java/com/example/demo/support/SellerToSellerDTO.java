package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Seller;
import com.example.demo.web.dto.SellerDTO;


@Component
public class SellerToSellerDTO implements Converter<Seller, SellerDTO> {


	@Override
	public SellerDTO convert(Seller seller) {
		SellerDTO dto = new SellerDTO();
		
		dto.setId(seller.getId());
		dto.setUsername(seller.getUsername());
		dto.setPassword(seller.getPassword());
		dto.setFirstName(seller.getFirstName());
		dto.setLastName(seller.getLastName());
		dto.setEmail(seller.getEmail());
		return dto;
	}
	
	public List<SellerDTO> convert(List<Seller> sellerList){
		List<SellerDTO> sellerDTOList = new ArrayList<>();
		
		for(Seller seller : sellerList){
			sellerDTOList.add(convert(seller));
		}
		
		return sellerDTOList;
	}
}
