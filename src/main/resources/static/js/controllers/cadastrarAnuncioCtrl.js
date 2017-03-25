angular.module("adExtreme")

.controller("cadastrarAnuncioCtrl", function($scope, RestService, $state, $http) {

	const rotaAdicaoDeAnuncio = "/usuario/cadastrar/anuncio";
	const rotaTiposDeAnuncios = "/usuario/anuncios/tipos/cadastrar";

	$scope.tiposDeAnuncio = [];

	carregarTiposDeAnuncio();

	/**
	 * Metodo que carrega os tipos de anuncios que o usuario pode cadastrar
	 * @returns resposta da requisicao HTTP
	 */
	function carregarTiposDeAnuncio() {
		RestService.find(rotaTiposDeAnuncios, function(tiposDeAnuncio) {
			$scope.tiposDeAnuncio = tiposDeAnuncio.data;
		});
	};

	/**
	 * Cadastra o anuncio no BD
	 * @returns reposta da requisicao HTTP
	 */
	$scope.cadastrarAnuncio = function(anuncioNovo) {
		RestService.add(rotaAdicaoDeAnuncio, anuncioNovo, function(response) {
			console.log(response);
			$state.go("home");
		});
	};

	/**
	 * Limpa as informações do anuncio
	 */
	$scope.limpar = function(anuncioNovo) {
		anuncioNovo = {};
	};

	$scope.$watchCollection('anuncioNovo',function(novo){
		console.log(novo);
	});
});