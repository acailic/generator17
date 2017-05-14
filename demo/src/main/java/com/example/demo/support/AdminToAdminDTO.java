package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.web.dto.AdminDTO;


@Component
public class AdminToAdminDTO implements Converter<Admin, AdminDTO> {


	@Override
	public AdminDTO convert(Admin admin) {
		AdminDTO dto = new AdminDTO();
		
		dto.setId(admin.getId());
		dto.setUsername(admin.getUsername());
		dto.setPassword(admin.getPassword());
		return dto;
	}
	
	public List<AdminDTO> convert(List<Admin> adminList){
		List<AdminDTO> adminDTOList = new ArrayList<>();
		
		for(Admin admin : adminList){
			adminDTOList.add(convert(admin));
		}
		
		return adminDTOList;
	}
}
