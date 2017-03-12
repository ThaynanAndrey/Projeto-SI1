angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state) {

	const rotaDePegarAnuncios = "/user/listar/anuncios";

	$scope.anuncios = [];

	pegarAnuncios();

	function pegarAnuncios() {
		RestService.find('http//localhost:8080/user/listar/anuncios', function(response) {
			$scope.anuncios = response.data;
		});
	};

	$scope.anunciosIsEmpty = function() {
		return $scope.anuncios.length === 0;
	};

});