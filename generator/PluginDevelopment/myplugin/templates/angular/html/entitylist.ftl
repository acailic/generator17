<#if class.uiClass??>
	
<h2>${class.uiClass.label}<span class="badge">{{totalItems}}</span></h2>
	
<div ng-init="page=1; getAll();">

	<#if class.uiClass.groupPresType == "table">
	
<table class="table table-hover">
	<thead>
		<tr>
			<#list properties as property>
				<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>  	
					<#if property.association == false || (property.zoom?? && property.zoom == true && property.zoomPresPropertyName??)>
			<td>${property.name?cap_first}</td>
					</#if>
				</#if>
			</#list>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="${class.name?uncap_first} in ${class.name?uncap_first}List">
			<#list properties as property>
				<#if property.upper == 1 && (!property.hidden?? || property.hidden == false)>
					<#if property.association == false> 
			<td>{{ ${class.name?uncap_first}.${property.name} }}</td>			
					<#elseif property.zoom?? && property.zoom == true && property.zoomPresPropertyName??>
			<td>{{ ${class.name?uncap_first}.${property.name}.${property.zoomPresPropertyName} }}</td>	
					</#if>
			
				</#if>
			</#list>
			<td>
				<a class="btn btn-success btn-sm" href="/#/${class.name?uncap_first}List/{{ ${class.name?uncap_first}.id }}">view</a>
				<a class="btn btn-warning btn-sm" href="/#/${class.name?uncap_first}List/edit/{{ ${class.name?uncap_first}.id }}">edit</a>
				<button class="btn btn-danger btn-sm" ng-click="remove(${class.name?uncap_first}.id)">delete</button>
			</td>
		</tr>
	</tbody>
</table>

	<#elseif class.uiClass.groupPresType == "grid">

<div class="row">
  	<div ng-repeat="${class.name?uncap_first} in ${class.name?uncap_first}List" class="col-md-3">
    	<div class="thumbnail">
	      	<div class="caption">
	      	<h3> {{${class.name?uncap_first}.${class.uiClass.presPropertyName}}} </h3>
	      	<#list properties as property>
				<#if property.upper == 1 && property.name != class.uiClass.presPropertyName && (!property.hidden?? || property.hidden == false)>
					<#if property.association == false> 
				<p>{{ ${class.name?uncap_first}.${property.name} }}</p>			
					<#elseif property.zoom?? && property.zoom == true && property.zoomPresPropertyName??>
				<p>{{ ${class.name?uncap_first}.${property.name}.${property.zoomPresPropertyName} }}</p>	
					</#if>
			
				</#if>
			</#list>
        	</div>
        	<div>
	      		<span>
					<a class="btn btn-success btn-sm" href="/#/${class.name?uncap_first}List/{{ ${class.name?uncap_first}.id }}">view</a>
	      		</span>
	      		<span>
					<a class="btn btn-warning btn-sm" href="/#/${class.name?uncap_first}List/edit/{{ ${class.name?uncap_first}.id }}">edit</a>
				</span>
				<span>
					<button class="btn btn-danger btn-sm" ng-click="remove(${class.name?uncap_first}.id)">delete</button>
	      		</span>
      		</div>
    	</div>
  	</div>
</div>

	</#if>
	
<div>

<div class="row text-center">
	<span ng-hide="hideSpinner" class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
	<span ng-show="showError" class="label label-danger">Ooops, something went wrong! :)</span>
</div>

<div class="row">
	<uib-pagination total-items="totalItems" items-per-page="{{maxSize}}" ng-model="page" ng-click="getAll()" max-size="maxSize" class="pagination-sm" boundary-links="true"></uib-pagination>
</div>

<a href="/#/${class.name?uncap_first}List/add">Add new ${class.name}</a>

<div class = "row">	
	<uib-alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</uib-alert>	
</div>	

</#if>