package ${class.typePackage}.service;

import ${class.typePackage}.model.${class.name};
import java.util.List;

public interface ${class.name}Service extends CrudService<${class.name}> {
	
	<#list properties as property>
		<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
			<#if property.association == false && property.findBy?? && property.findBy == true> 
	List<${class.name}> findBy${property.name?cap_first}(${property.type} ${property.name});
	
			<#elseif property.association == true && property.zoom?? && property.zoom == true>
	List<${class.name}> findBy${property.type}Id(Long id);
	
			</#if>
		</#if>
 	</#list>
}
