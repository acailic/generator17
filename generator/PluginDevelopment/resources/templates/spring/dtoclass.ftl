package ${class.typePackage}.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

${class.visibility} class ${class.name}DTO extends AbstractBaseDTO { 

<#list properties as property>
	<#if property.upper == 1 >   
		<#if !property.hidden?? || property.hidden == false>
			<#if property.association == false>
    			<#if property.validationProperty??> 
    				<#if property.validationProperty.notNull?? && property.validationProperty.notNull == true>
	@NotNull
	    			</#if>
	    			<#if property.validationProperty.minLength?? && property.validationProperty.minLength??>
    @Size(min=${property.validationProperty.minLength}, max=${property.validationProperty.maxLength})
	    			</#if>
	    			<#if property.validationProperty.minValue??>
    @Min(${property.validationProperty.minValue})
	    			</#if>
	    			<#if property.validationProperty.maxValue??>
    @Max(${property.validationProperty.maxValue})	
    				</#if>
    			</#if>
    ${property.visibility} ${property.type} ${property.name};
    		<#else>
    ${property.visibility} ${property.type}DTO ${property.name};	
    		</#if>
    	</#if>
    <#elseif property.upper == -1 > 
    	<#if property.association == false>
	${property.visibility} List<${property.type}> ${property.name} = new ArrayList<${property.type}>();
		</#if>
    <#else>   
    	<#list 1..property.upper as i>
  	${property.visibility} ${property.type} ${property.name}${i};
		</#list>  
    </#if>     
</#list>

	public ${class.name}DTO() {}

<#list properties as property>
	<#if property.upper == 1 >  
		<#if !property.hidden?? || property.hidden == false> 
			<#if property.association == false>
	public ${property.type} get${property.name?cap_first}(){
    	return ${property.name};
  	}
  
  	public void set${property.name?cap_first}(${property.type} ${property.name}){
       	this.${property.name} = ${property.name};	
	}	
			<#else>
	public ${property.type}DTO get${property.name?cap_first}(){
    	return ${property.name};
  	}
  
  	public void set${property.name?cap_first}(${property.type}DTO ${property.name}){
       	this.${property.name} = ${property.name};	
	}	
			</#if>
		</#if>
    <#elseif property.upper == -1 >
    	<#if property.association == false>	
   	public Set<${property.type}> get${property.name?cap_first}(){
     	return ${property.name};
    }
      
   	public void set${property.name?cap_first}( Set<${property.type}> ${property.name}){
     	this.${property.name} = ${property.name};
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
