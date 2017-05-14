package com.example.demo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.web.dto.AdminDTO;


@Component
public class AdminDTOToAdmin
	implements Converter<AdminDTO, Admin> {
	
	
	@Autowired
	AdminService adminService;

	@Override
	public Admin convert(AdminDTO dto) {
		Admin admin = new Admin();
		
		if(dto.getId()!=null){
			admin = adminService.findOne(dto.getId());
			
			if(admin == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant admin");
			}
		}
		
		admin.setId(dto.getId());
		
	    admin.setUsername(dto.getUsername());
	    admin.setPassword(dto.getPassword());
		
		
		return admin;
	}
	
	public List<Admin> convert (List<AdminDTO> adminDTOList){
		List<Admin> adminList = new ArrayList<>();
		
		for(AdminDTO dto : adminDTOList){
			adminList.add(convert(dto));
		}
		
		return adminList;
	}

}