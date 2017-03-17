angular.module("adExtreme")

.controller("anunciosCtrl", function($scope, RestService, $state, $http) {

	$scope.testar = function() {
		console.log("Chamouuu");
		
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/usuario/listar/anuncios',
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
	
	$scope.testarUsuario = function() {
	
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/usuario/usuarioLogado',
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
	
	$scope.meusAnuncios = function() {
		
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/usuario/logado/anuncios',
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