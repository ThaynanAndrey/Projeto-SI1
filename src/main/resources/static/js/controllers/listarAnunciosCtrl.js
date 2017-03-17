angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaDePegarAnuncios = "/usuario/listar/anuncios/comprar";
	const rotaDonoDoAnuncio = "/usuario/dono/anuncio/";
	const rotaDecomprarAnuncio = "/usuario/comprar/anuncio";

	$scope.anuncios = [];

	pegarAnuncios();

	function pegarAnuncios() {
		RestService.find(rotaDePegarAnuncios, function(response) {
			console.log(response);
			$scope.anuncios = response.data;
		});
	};

	$scope.anunciosIsEmpty = function() {
		return $scope.anuncios.length === 0;
	};

	$scope.pegarDono = function(id) {
		
		RestService.find(rotaDonoDoAnuncio + id, function(response) {
			console.log(response);
		});	
	};
	
	$scope.comprarAnuncio = function(anuncio) {
		
		anuncioComprado = {
	        	titulo: anuncio.titulo,
	        	quantia: anuncio.quantia,
	        	tipo: anuncio.tipo,
	        	id: anuncio._id
	        }; 
		
		RestService.add(rotaDecomprarAnuncio, anuncioComprado, function(response) {
			console.log(response);
		});
	};
});