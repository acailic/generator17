package ${class.typePackage}.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${class.typePackage}.model.${class.name};


@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Long> {

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
