var demoApp = angular.module('demoApp.routes', ['ngRoute']);

demoApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        <#list classes as class>
        .when('/${class.name?uncap_first}List', {
            templateUrl : '/static/app/html/partial/${class.name?uncap_first}List.html',
            controller : '${class.name}Controller'
        })
        .when('/${class.name?uncap_first}List/add', {
            templateUrl : '/static/app/html/partial/addEdit${class.name}.html',
            controller : '${class.name}Controller'
        })
        .when('/${class.name?uncap_first}List/edit/:id', {
            templateUrl : '/static/app/html/partial/addEdit${class.name}.html',
            controller : '${class.name}Controller'
        })
        .when('/${class.name?uncap_first}List/:id', {
            templateUrl : '/static/app/html/partial/view${class.name}.html',
            controller : '${class.name}Controller'
        })
        </#list>
        .otherwise({
            redirectTo: '/'
        });
}]);
