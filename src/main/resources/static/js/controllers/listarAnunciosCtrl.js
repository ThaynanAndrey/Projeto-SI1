angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state) {

	const rotaDePegarAnuncios = "/user/listar/anuncioss";

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

});