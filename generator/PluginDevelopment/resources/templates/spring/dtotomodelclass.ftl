package ${class.typePackage}.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.service.${class.name}Service;
import ${class.typePackage}.web.dto.${class.name}DTO;

<#list properties as property>
	<#if property.upper == 1 && property.association == true>   
import com.example.demo.support.${property.type}DTOTo${property.type};
	</#if>
</#list>

@Component
public class ${class.name}DTOTo${class.name}
	implements Converter<${class.name}DTO, ${class.name}> {
	
	<#list properties as property>
		<#if property.upper == 1 && property.association == true>   
	@Autowired
	private ${property.type}DTOTo${property.type} to${property.type};
		</#if>
	</#list>
	
	@Autowired
	${class.name}Service ${class.name?uncap_first}Service;

	@Override
	public ${class.name} convert(${class.name}DTO dto) {
		${class.name} ${class.name?uncap_first} = new ${class.name}();
		
		if(dto.getId()!=null){
			${class.name?uncap_first} = ${class.name?uncap_first}Service.findOne(dto.getId());
			
			if(${class.name?uncap_first} == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant ${class.name?uncap_first}");
			}
		}
		
		${class.name?uncap_first}.setId(dto.getId());
		
		<#list properties as property>
			<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
				<#if property.association == false>
	    ${class.name?uncap_first}.set${property.name?cap_first}(dto.get${property.name?cap_first}());
				<#else>
		${class.name?uncap_first}.set${property.type}(to${property.type}.convert(dto.get${property.type}()));
				</#if>
			</#if>
		</#list>
		
		
		return ${class.name?uncap_first};
	}
	
	public List<${class.name}> convert (List<${class.name}DTO> ${class.name?uncap_first}DTOList){
		List<${class.name}> ${class.name?uncap_first}List = new ArrayList<>();
		
		for(${class.name}DTO dto : ${class.name?uncap_first}DTOList){
			${class.name?uncap_first}List.add(convert(dto));
		}
		
		return ${class.name?uncap_first}List;
	}

}