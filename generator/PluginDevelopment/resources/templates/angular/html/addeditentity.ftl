<h2>{{addEditHeading}} ${class.name}</h2>

<form ng-init="initAddEditPage(); getOne()" class="form-horizontal">
	<#list properties as property>
		<#if !property.hidden?? || property.hidden == false>
			<#if property.upper == 1>
	    		<#if property.association == false && property.uiProperty??>
	<div class="form-group">
	    <label class="col-sm-2 control-label">${property.name?cap_first}</label>
	    <div class="col-sm-4">
	    	<input type="${property.uiProperty.formType}" class="form-control" placeholder="${property.name?cap_first}..." ng-model="${class.name?uncap_first}.${property.name}" <#if property.uiProperty.readOnly?? && property.uiProperty.readOnly == true> ng-disabled="addEditHeading == 'Edit'"</#if>>
	    </div>
	    		<#elseif property.zoom?? && property.zoom == true && property.zoomPresPropertyName??> 
	<div class="form-group">
	    <label class="col-sm-2 control-label">${property.name?cap_first}</label>
	    <div class="col-sm-4">
	    	<select class="form-control" ng-model="${class.name?uncap_first}.${property.name}">
	    		<option ng-repeat="${property.name} in ${property.name}List" value="{{${property.name}}}"> {{${property.name}.${property.zoomPresPropertyName}}} </option>
	    	</select>
	    </div>
	    		</#if>  
 	</div>
 			</#if>
 		</#if>
	</#list>

	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	    	<#assign i = 0 />
	    	<button ng-disabled="<#list properties as property> <#if property.upper == 1> <#if i != 0>&&</#if> !${class.name?uncap_first}.${property.name} <#assign i = i+1 /> </#if></#list>"
	    			ng-click="open()" type="submit" class="btn btn-info">Save</button>
	    </div>
  	</div>
</form>

<script type="text/ng-template" id="myModalContent.html">
	<div class="modal-header">
	   	<h3 class="modal-title">Confirmation</h3>
	</div>
	<div class="modal-body">
	   	<p>Are you sure you want to add/edit activity?</p>
	</div>
	<div class="modal-footer">
	   	<button class="btn btn-primary" type="button" ng-click="confirm()">Confirm</button>
	   	<button class="btn btn-warning" type="button" ng-click="revert()">Revert</button>
	</div>
</script>

<div class = "row">	
	<uib-alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</uib-alert>	
</div>	
	
