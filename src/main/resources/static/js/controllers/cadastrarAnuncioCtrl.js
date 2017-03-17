angular.module("adExtreme")

.controller("cadastrarAnuncioCtrl", function($scope, RestService, $state, $http) {

	const rotaAdicaoDeAnuncio = "/usuario/cadastrar/anuncio";
	const rotaTiposDeAnuncios = "/usuario/anuncios/tipos/cadastrar";

	$scope.tiposDeAnuncio = [];
	carregarTiposDeAnuncio();

	function carregarTiposDeAnuncio() {
		RestService.find(rotaTiposDeAnuncios, function(tiposDeAnuncio) {
			console.log(tiposDeAnuncio.data);
			$scope.tiposDeAnuncio = tiposDeAnuncio.data;
		});
	};

	$scope.cadastrarAnuncio = function(anuncioNovo) {
		
		RestService.add(rotaAdicaoDeAnuncio, anuncioNovo, function(response) {
			console.log(response);
		});
	};

	$scope.limpar = function(anuncioNovo) {
		anuncioNovo = {};
	};
});