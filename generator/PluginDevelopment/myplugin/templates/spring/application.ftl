package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

<#list classes as class>
import ${class.typePackage}.model.${class.name};
</#list>

<#list classes as class>
import ${class.typePackage}.service.${class.name}Service;
</#list>


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@Component
class DemoCommandLineRunner implements CommandLineRunner {
	
	<#list classes as class>
	@Autowired
	${class.name}Service ${class.name?uncap_first}Service;
	</#list>
	
	
	@Override
	public void run(String... arg0) throws Exception {
		
		<#assign x = 2>
		<#list classes as class>
		// ${class.name}
			<#list 1..x as i> 
		${class.name} ${class.name?uncap_first}${i?long} = new ${class.name}();
				<#list class.properties as property>
					<#if property.upper == 1 > 
						<#if property.association == true>
							<#if i%2 == 1>
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}(${property.name}1);
							<#else>
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}(${property.name}2);				
							</#if>
						<#elseif property.type == "String">
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}("${property.name}${i?long}");
						<#elseif property.type?lower_case == "Boolean"?lower_case>
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}(false);	
						<#elseif property.type == "Integer" || property.type == "int">
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}(10);	
						<#elseif property.type?lower_case == "Double"?lower_case>
		${class.name?uncap_first}${i?long}.set${property.name?cap_first}(10.3);		
						</#if>
					</#if>
				</#list>
		${class.name?uncap_first}Service.save(${class.name?uncap_first}${i?long});
			</#list>
		<#assign x = x*2>
		</#list>

	}
	
}
