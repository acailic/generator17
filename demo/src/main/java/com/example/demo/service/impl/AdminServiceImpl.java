package com.example.demo.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin findOne(Long id) {
		return adminRepository.findOne(id);
	}

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}
		
	@Override
	public Page<Admin> findAll(int page) {
		return adminRepository.findAll(new PageRequest(page, 12));
	}

	@Override
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin remove(Long id) {
		Admin admin = adminRepository.findOne(id);
		if(admin == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant Admin");
		}
		adminRepository.delete(admin);
		return admin;
	}
	
	
}
