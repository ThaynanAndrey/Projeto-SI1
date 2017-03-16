angular.module("adExtreme")

.controller("cadastrarAnuncioCtrl", function($scope, RestService, $state, $http) {

	const rotaAdicaoDeAnuncio = "/user/cadastrar/anuncio";
	const rotaTiposDeAnuncio = "/tiposDeAnuncio";

	$scope.tiposDeAnuncio = ["emprego"];
	//carregarTiposDeAnuncio();

	function carregarTiposDeAnuncio() {
		RestService.find(rotaTiposDeAnuncio, function(tiposDeAnuncio) {
			$scope.tiposDeAnuncio = tiposDeAnuncio;
		});
	};

	$scope.cadastrarAnuncio = function(anuncioNovo) {
		
		 $http({
		        url: 'http://localhost:8080/user/cadastrar/anuncio',
		        method: "POST",
		        headers: {
			 		'Content-type': 'application/json',
			 		'Cross-Origin': "*" 
		        },
		        data: anuncioNovo
		    })
		    .then(function(response) {
		    	console.log("certo");
		    	console.log(response);
		    }, 
		    function(response) {
		    	console.log("errado");
		    	console.log(response);
		    });
	};

	$scope.limpar = function(anuncioNovo) {
		anuncioNovo = {};
	};
});