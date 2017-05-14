package ${class.typePackage}.service.impl;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.repository.${class.name}Repository;
import ${class.typePackage}.service.${class.name}Service;


@Service
@Transactional
public class ${class.name}ServiceImpl implements ${class.name}Service {

	@Autowired
	private ${class.name}Repository ${class.name?uncap_first}Repository;
	
	@Override
	public ${class.name} findOne(Long id) {
		return ${class.name?uncap_first}Repository.findOne(id);
	}

	@Override
	public List<${class.name}> findAll() {
		return ${class.name?uncap_first}Repository.findAll();
	}
		
	@Override
	public Page<${class.name}> findAll(int page) {
		return ${class.name?uncap_first}Repository.findAll(new PageRequest(page, 12));
	}

	@Override
	public ${class.name} save(${class.name} ${class.name?uncap_first}) {
		return ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
	}
	
	public ${class.name} remove(Long id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Repository.findOne(id);
		if(${class.name?uncap_first} == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant ${class.name}");
		}
		${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
		return ${class.name?uncap_first};
	}
	
	<#list properties as property>
		<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
			<#if property.association == false && property.findBy?? && property.findBy == true> 
	public List<${class.name}> findBy${property.name?cap_first}(${property.type} ${property.name}) {
		return ${class.name?uncap_first}Repository.findBy${property.name?cap_first}(${property.name});
	}
	
			<#elseif property.association == true && property.zoom?? && property.zoom == true>
	public List<${class.name}> findBy${property.type}Id(Long id) {
		return ${class.name?uncap_first}Repository.findBy${property.type}Id(id);
	}
	
			</#if>
		</#if>
 	</#list>
	
}
