package ${class.typePackage}.web.controller;

import java.util.List;

import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.service.${class.name}Service;
import ${class.typePackage}.support.${class.name}DTOTo${class.name};
import ${class.typePackage}.support.${class.name}To${class.name}DTO;
import ${class.typePackage}.web.dto.${class.name}DTO;

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
@RequestMapping(value = "/api/${class.name?uncap_first}List")
public class Api${class.name}Controller {

	@Autowired
	private ${class.name}Service ${class.name?uncap_first}Service;

	@Autowired
	private ${class.name}To${class.name}DTO toDTO;

	@Autowired
	private ${class.name}DTOTo${class.name} to${class.name};

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<${class.name}DTO>> get${class.name}List(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false) Integer page) {

		List<${class.name}> ${class.name?uncap_first}List;
		int totalItems = 0;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Page<${class.name}> ${class.name?uncap_first}ListPage;
		
		if(page != null) {
			${class.name?uncap_first}ListPage = ${class.name?uncap_first}Service.findAll(page);
			totalItems = ${class.name?uncap_first}Service.findAll().size();
			httpHeaders.add("total-items", "" + totalItems);
			${class.name?uncap_first}List = ${class.name?uncap_first}ListPage.getContent();
		}
		else {
			${class.name?uncap_first}List = ${class.name?uncap_first}Service.findAll();
		}
	
		return new ResponseEntity<>(toDTO.convert(${class.name?uncap_first}List), httpHeaders, HttpStatus.OK);
	}
	
	<#list properties as property>
		<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
			<#if property.association == true && property.zoom?? && property.zoom == true>
	@RequestMapping(value = "/filterBy${property.type}/{id}", method = RequestMethod.GET)
		ResponseEntity<List<${class.name}DTO>> get${class.name}ListBy${property.type}(@PathVariable Long id) {

		List<${class.name}> ${class.name?uncap_first}List;
		
		${class.name?uncap_first}List = ${class.name?uncap_first}Service.findBy${property.type}Id(id);
			
		return new ResponseEntity<>(toDTO.convert(${class.name?uncap_first}List), HttpStatus.OK);
	}
	
			</#if>
		</#if>
 	</#list>

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<${class.name}DTO> get${class.name}(@PathVariable Long id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Service.findOne(id);
		if (${class.name?uncap_first} == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(${class.name?uncap_first}), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<${class.name}DTO> delete(@PathVariable Long id) {
		${class.name} deleted = ${class.name?uncap_first}Service.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<${class.name}DTO> add(@RequestBody @Valid ${class.name}DTO new${class.name}) {

		${class.name} saved${class.name} = ${class.name?uncap_first}Service.save(to${class.name}
				.convert(new${class.name}));

		return new ResponseEntity<>(toDTO.convert(saved${class.name}), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<${class.name}DTO> edit(@RequestBody @Valid ${class.name}DTO ${class.name?uncap_first},
			@PathVariable Long id) {

		if (id != ${class.name?uncap_first}.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		${class.name} persisted = ${class.name?uncap_first}Service.save(to${class.name}.convert(${class.name?uncap_first}));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
