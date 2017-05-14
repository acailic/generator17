package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.SportShop;
import com.example.demo.web.dto.SportShopDTO;

import com.example.demo.support.SportShopBrandToSportShopBrandDTO;
import com.example.demo.support.CityToCityDTO;

@Component
public class SportShopToSportShopDTO implements Converter<SportShop, SportShopDTO> {

	@Autowired
	private SportShopBrandToSportShopBrandDTO toSportShopBrandDTO;
	@Autowired
	private CityToCityDTO toCityDTO;

	@Override
	public SportShopDTO convert(SportShop sportShop) {
		SportShopDTO dto = new SportShopDTO();
		
		dto.setId(sportShop.getId());
		dto.setName(sportShop.getName());
		dto.setStreetName(sportShop.getStreetName());
		dto.setStreetNumber(sportShop.getStreetNumber());
		dto.setGpsLatitude(sportShop.getGpsLatitude());
		dto.setSportShopBrand(toSportShopBrandDTO.convert(sportShop.getSportShopBrand()));
		dto.setCity(toCityDTO.convert(sportShop.getCity()));
		dto.setGpsLongitude(sportShop.getGpsLongitude());
		return dto;
	}
	
	public List<SportShopDTO> convert(List<SportShop> sportShopList){
		List<SportShopDTO> sportShopDTOList = new ArrayList<>();
		
		for(SportShop sportShop : sportShopList){
			sportShopDTOList.add(convert(sportShop));
		}
		
		return sportShopDTOList;
	}
}
