angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaDePegarAnuncios = "/user/listar/anuncios";

	$scope.anuncios = [];

	pegarAnuncios();

	function pegarAnuncios() {
		RestService.find(rotaDePegarAnuncios, function(response) {
			$scope.anuncios = response.data;
		});
	};

	$scope.anunciosIsEmpty = function() {
		return $scope.anuncios.length === 0;
	};

	$scope.pegarDono = function(id) {
		$http({
			  method: 'GET',
			  url: 'http://localhost:8080/user/anuncio/' + id,
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