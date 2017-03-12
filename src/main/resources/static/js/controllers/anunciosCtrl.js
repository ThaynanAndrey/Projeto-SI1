angular.module("adExtreme")

.controller("anunciosCtrl", function($scope, RestService, $state, $http) {

	$scope.testar = function() {
		console.log("Chamouuu");
		
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/user/listar/anuncioss',
		      headers: {
                  'Cross-Origin': "*"
       			}
		
			}).then(function successCallback(response) {
					console.log("certo");
					console.log(response.data);
			  }, function errorCallback(response) {
				  console.log("errado");
				  console.log(response);
			  });
	};
});