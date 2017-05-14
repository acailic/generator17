package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.SportShopBrand;
import com.example.demo.service.SportShopBrandService;
import com.example.demo.web.dto.SportShopBrandDTO;

import com.example.demo.support.SellerDTOToSeller;

@Component
public class SportShopBrandDTOToSportShopBrand
	implements Converter<SportShopBrandDTO, SportShopBrand> {
	
	@Autowired
	private SellerDTOToSeller toSeller;
	
	@Autowired
	SportShopBrandService sportShopBrandService;

	@Override
	public SportShopBrand convert(SportShopBrandDTO dto) {
		SportShopBrand sportShopBrand = new SportShopBrand();
		
		if(dto.getId()!=null){
			sportShopBrand = sportShopBrandService.findOne(dto.getId());
			
			if(sportShopBrand == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant sportShopBrand");
			}
		}
		
		sportShopBrand.setId(dto.getId());
		
	    sportShopBrand.setName(dto.getName());
		sportShopBrand.setSeller(toSeller.convert(dto.getSeller()));
		
		
		return sportShopBrand;
	}
	
	public List<SportShopBrand> convert (List<SportShopBrandDTO> sportShopBrandDTOList){
		List<SportShopBrand> sportShopBrandList = new ArrayList<>();
		
		for(SportShopBrandDTO dto : sportShopBrandDTOList){
			sportShopBrandList.add(convert(dto));
		}
		
		return sportShopBrandList;
	}

}