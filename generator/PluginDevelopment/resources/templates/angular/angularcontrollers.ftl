var demoApp = angular.module('demoApp.controllers', ['ui.bootstrap']);

<#list classes as class>
demoApp.controller('${class.name}Controller', function($scope, $location, $routeParams, $uibModal,
	<#list class.properties as property><#if property.upper == 1 && property.association == true>${property.name}Service, </#if></#list> 
	<#list class.properties as property><#if property.upper == -1 && property.association == true && property.next?? && property.next == true>${property.type?uncap_first}Service, </#if></#list>
	${class.name?uncap_first}Service) {
	
	$scope.reverse = true;
	
	$scope.changeReverse = function() {
		$scope.${class.name?uncap_first}List = $scope.${class.name?uncap_first}List.slice().reverse();
	}
	
	$scope.maxSize = 12;
	
	$scope.open = function () {
		if(!$routeParams.if) {
		<#list class.properties as property>
			<#if property.upper == 1 && property.association == true>
		$scope.${class.name?uncap_first}.${property.name} = JSON.parse($scope.${class.name?uncap_first}.${property.name});
			</#if>
		</#list>
		}
		var modalInstance = $uibModal.open({
			  animation: true,
		      templateUrl: 'myModalContent.html',
		      controller: 'addEdit${class.name}ConfirmationController',
		      size : 'sm',
		      resolve: {
		          ${class.name?uncap_first}: function () {
		            return $scope.${class.name?uncap_first};
		          }
		      }
		});	
		
		modalInstance.result.then(function () {
				$location.path('/${class.name?uncap_first}List');
		    }, function () {
		});
		
	}
	
	$scope.alerts = [];
	
	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	};
	
	$scope.getAll = function() {
		${class.name?uncap_first}Service.getAll($scope.search, $scope.page-1)
				.success(function(data, status, headers) {
					$scope.${class.name?uncap_first}List = data;
					$scope.hideSpinner = true;
					$scope.totalItems = headers('total-items');
				})
				.error(function() {
					$scope.showError = true;
					$scope.hideSpinner = true;
				});
	};
	
	$scope.remove = function(id) {
		${class.name?uncap_first}Service.remove(id)
				.success(function(data) {
					$scope.getAll();
					$scope.alerts.push({msg: '${class.name} with ID ' + id + ' successufully deleted', type: 'success'});
				})
				.error(function() {
					$scope.alerts.push({msg: 'Error while deleting ${class.name} with ID ' + id + '!', type: 'danger'});
				});
	};
	
	$scope.addEditHeading;
	
	$scope.initAddEditPage = function () {
		if ($routeParams.id) {
			$scope.addEditHeading = 'Edit';
		}
		else {
			$scope.addEditHeading = 'Add';
		}
	}
	
	$scope.getOne = function() {
		$scope.${class.name?uncap_first} = {};
		<#list class.properties as property>
			<#if property.upper == 1 && property.association == true>
			${property.name}Service.getAll()
				.success(function(data) {
						$scope.${property.name}List = data;
					});
			</#if>
		</#list>
		if ($routeParams.id) {  // edit stranica
			${class.name?uncap_first}Service.getOne($routeParams.id)
					.success(function(data) {
						$scope.${class.name?uncap_first} = data;
						<#list class.properties as property>
							<#if property.upper == -1 && property.association == true && property.next?? && property.next == true>
						${property.type?uncap_first}Service.getAllBy${class.name}($scope.${class.name?uncap_first}.id)
							.success(function(data) {
								$scope.${property.type?uncap_first}List = data;
							})
							.error(function() {
								
							});
							</#if>
						</#list>
						
						
					})
					.error(function() {
						$scope.alerts.push({msg: '${class.name} with ID ' + $routeParams.id + ' does not exist!', type: 'danger'});
					});
		}
	};
	
});


demoApp.controller('addEdit${class.name}ConfirmationController', function($scope, $uibModalInstance, ${class.name?uncap_first}Service, ${class.name?uncap_first}) {
	
	$scope.${class.name?uncap_first} = ${class.name?uncap_first};
	
	$scope.confirm = function () {
		$scope.save();
	};

	$scope.revert = function () {
	    $uibModalInstance.dismiss();
	};
	
	$scope.save = function() {
		${class.name?uncap_first}Service.save($scope.${class.name?uncap_first})
				.success(function() {
					$uibModalInstance.close();
				})
				.error(function() {
					$uibModalInstance.dismiss();
				});
	};
	
});
</#list>