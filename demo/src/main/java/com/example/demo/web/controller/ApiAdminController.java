package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.support.AdminDTOToAdmin;
import com.example.demo.support.AdminToAdminDTO;
import com.example.demo.web.dto.AdminDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/adminList")
public class ApiAdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminToAdminDTO toDTO;

	@Autowired
	private AdminDTOToAdmin toAdmin;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AdminDTO>> getAdminList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<Admin> adminList;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<Admin> adminListPage;
		
		if(page != null) {
			adminListPage = adminService.findAll(page);
			totalItems = adminService.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			adminList = adminListPage.getContent();
		}
		else {
			adminList = adminService.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(adminList), httpHeaders, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) {
		Admin admin = adminService.findOne(id);
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(admin), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AdminDTO> delete(@PathVariable Long id) {
		Admin deleted = adminService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdminDTO> add(@RequestBody @Valid AdminDTO newAdmin) {

		Admin savedAdmin = adminService.save(toAdmin
				.convert(newAdmin));

		return new ResponseEntity<>(toDTO.convert(savedAdmin), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AdminDTO> edit(@RequestBody @Valid AdminDTO admin,
			@PathVariable Long id) {

		if (id != admin.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Admin persisted = adminService.save(toAdmin.convert(admin));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
