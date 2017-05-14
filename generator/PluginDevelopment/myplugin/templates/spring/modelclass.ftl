package ${class.typePackage}.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name="${class.name?lower_case}")
@Entity
${class.visibility} class ${class.name} extends AbstractBaseEntity { 
 
<#list properties as property>
	<#if property.upper == 1 >   
		<#if property.association == true>
	@ManyToOne(fetch=FetchType.LAZY)
		<#else>
	@Column(name="${property.name?lower_case}"
	<#if property.validationProperty?? && property.validationProperty.unique?? && property.validationProperty.unique == true>, unique=true</#if>)
		</#if>
    ${property.visibility} ${property.type} ${property.name};
    <#elseif property.upper == -1 > 
    	<#if property.association == true>
    @OneToMany(mappedBy="${class.name?uncap_first}",cascade=CascadeType.REMOVE)
	${property.visibility} List<${property.type}> ${property.name}List = new ArrayList<${property.type}>();
		</#if>
    <#else>   
    	<#list 1..property.upper as i>
  	${property.visibility} ${property.type} ${property.name}${i};
		</#list>  
    </#if> 
        
</#list>

	public ${class.name}() {}

<#list properties as property>
	<#if property.upper == 1 >   
  	public ${property.type} get${property.name?cap_first}(){
    	return ${property.name};
  	}
  
  	public void set${property.name?cap_first}(${property.type} ${property.name}){
       	this.${property.name} = ${property.name};	
	}
      
    <#elseif property.upper == -1 >
    	<#if property.association == false>
   	public Set<${property.type}> get${property.name?cap_first}(){
     	return ${property.name};
    }
      
   	public void set${property.name?cap_first}( Set<${property.type}> ${property.name}){
     	this.${property.name} = ${property.name};
   	}
   		<#else>
   	public void add${property.name?cap_first}(${property.type} ${property.name}){
		this.${property.name}List.add(${property.name});
		
		if(${property.name}.get${class.name}() != this){
			${property.name}.set${class.name}(this);
		}
	}
	
	public void remove${property.name?cap_first}(${property.type} ${property.name}){
		${property.name}.set${class.name}(null);
		${property.name}List.remove(${property.name});
	}
	
      	</#if>
    <#else>   
    	<#list 1..property.upper as i>
   	public ${property.type} get${property.name?cap_first}${i}(){
   		return ${property.name}${i};
   	}
      
  	public void set${property.name?cap_first}${i}(${property.type} ${property.name}${i}){
     	this.${property.name}${i} = ${property.name}${i};
  	}
            
		</#list>  
    </#if>     
</#list>

}




