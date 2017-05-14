package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.SportShop;
import com.example.demo.service.SportShopService;
import com.example.demo.web.dto.SportShopDTO;

import com.example.demo.support.SportShopBrandDTOToSportShopBrand;
import com.example.demo.support.CityDTOToCity;

@Component
public class SportShopDTOToSportShop
	implements Converter<SportShopDTO, SportShop> {
	
	@Autowired
	private SportShopBrandDTOToSportShopBrand toSportShopBrand;
	@Autowired
	private CityDTOToCity toCity;
	
	@Autowired
	SportShopService sportShopService;

	@Override
	public SportShop convert(SportShopDTO dto) {
		SportShop sportShop = new SportShop();
		
		if(dto.getId()!=null){
			sportShop = sportShopService.findOne(dto.getId());
			
			if(sportShop == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant sportShop");
			}
		}
		
		sportShop.setId(dto.getId());
		
	    sportShop.setName(dto.getName());
	    sportShop.setStreetName(dto.getStreetName());
	    sportShop.setStreetNumber(dto.getStreetNumber());
	    sportShop.setGpsLatitude(dto.getGpsLatitude());
		sportShop.setSportShopBrand(toSportShopBrand.convert(dto.getSportShopBrand()));
		sportShop.setCity(toCity.convert(dto.getCity()));
	    sportShop.setGpsLongitude(dto.getGpsLongitude());
		
		
		return sportShop;
	}
	
	public List<SportShop> convert (List<SportShopDTO> sportShopDTOList){
		List<SportShop> sportShopList = new ArrayList<>();
		
		for(SportShopDTO dto : sportShopDTOList){
			sportShopList.add(convert(dto));
		}
		
		return sportShopList;
	}

}