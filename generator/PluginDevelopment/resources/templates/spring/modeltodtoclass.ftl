package ${class.typePackage}.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.web.dto.${class.name}DTO;

<#list properties as property>
	<#if property.upper == 1 && property.association == true>  
import com.example.demo.support.${property.type}To${property.type}DTO;
	</#if>
</#list>

@Component
public class ${class.name}To${class.name}DTO implements Converter<${class.name}, ${class.name}DTO> {

	<#list properties as property>
		<#if property.upper == 1 && property.association == true>   
	@Autowired
	private ${property.type}To${property.type}DTO to${property.type}DTO;
		</#if>
	</#list>

	@Override
	public ${class.name}DTO convert(${class.name} ${class.name?uncap_first}) {
		${class.name}DTO dto = new ${class.name}DTO();
		
		dto.setId(${class.name?uncap_first}.getId());
		<#list properties as property>
			<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
				<#if property.association == false>
		dto.set${property.name?cap_first}(${class.name?uncap_first}.get${property.name?cap_first}());
				<#else>
		dto.set${property.type}(to${property.type}DTO.convert(${class.name?uncap_first}.get${property.type}()));
				</#if>
			</#if>
		</#list>
		return dto;
	}
	
	public List<${class.name}DTO> convert(List<${class.name}> ${class.name?uncap_first}List){
		List<${class.name}DTO> ${class.name?uncap_first}DTOList = new ArrayList<>();
		
		for(${class.name} ${class.name?uncap_first} : ${class.name?uncap_first}List){
			${class.name?uncap_first}DTOList.add(convert(${class.name?uncap_first}));
		}
		
		return ${class.name?uncap_first}DTOList;
	}
}
