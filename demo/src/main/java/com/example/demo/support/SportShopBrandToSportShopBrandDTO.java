package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.SportShopBrand;
import com.example.demo.web.dto.SportShopBrandDTO;

import com.example.demo.support.SellerToSellerDTO;

@Component
public class SportShopBrandToSportShopBrandDTO implements Converter<SportShopBrand, SportShopBrandDTO> {

	@Autowired
	private SellerToSellerDTO toSellerDTO;

	@Override
	public SportShopBrandDTO convert(SportShopBrand sportShopBrand) {
		SportShopBrandDTO dto = new SportShopBrandDTO();
		
		dto.setId(sportShopBrand.getId());
		dto.setName(sportShopBrand.getName());
		dto.setSeller(toSellerDTO.convert(sportShopBrand.getSeller()));
		return dto;
	}
	
	public List<SportShopBrandDTO> convert(List<SportShopBrand> sportShopBrandList){
		List<SportShopBrandDTO> sportShopBrandDTOList = new ArrayList<>();
		
		for(SportShopBrand sportShopBrand : sportShopBrandList){
			sportShopBrandDTOList.add(convert(sportShopBrand));
		}
		
		return sportShopBrandDTOList;
	}
}
