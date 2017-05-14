var demoApp = angular.module('demoApp.services', []);

<#list classes as class>
demoApp.service('${class.name?uncap_first}Service', function($http) {
	
	this.url = 'api/${class.name?uncap_first}List';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	<#list class.properties as property>
		<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
			<#if property.association == true && property.zoom?? && property.zoom == true>
	this.getAllBy${property.type} = function(id) {
		return $http.get(this.url + "/filterBy${property.type}/" + id);
	};
			</#if>
		</#if>
 	</#list>
	
	this.save = function(${class.name?uncap_first}) {
		if (${class.name?uncap_first}.id) {
			return $http.put(this.url + '/' + ${class.name?uncap_first}.id, ${class.name?uncap_first});
		} else {
			return $http.post(this.url, ${class.name?uncap_first});
		}
	};
});
</#list>
