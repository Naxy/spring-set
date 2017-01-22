var actionApp = angular.module("actionApp",['ngRoute']);

actionApp.config(['$routeProvider',function($routeProvider){
	$routeProvider.when('/oper',{
		controller:'View1Controller',
		templateUrl:'templates/argularjs/view1.html'
	}).when('directive',{
		controller:'View2Controller',
		templateUrl:'templates/argularjs/view2.html'
	});
}]);