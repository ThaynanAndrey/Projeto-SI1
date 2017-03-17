angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaDePegarAnuncios = "/usuario/listar/anuncios/comprar";

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
	
	$scope.comprarAnuncio = function(anuncio) {
		$http({
	        url: 'http://localhost:8080/usuario/comprar/anuncio',
	        method: "POST",
	        headers: {
		 		'Content-type': 'application/json',
		 		'Cross-Origin': "*" 
	        },
	        data: {
	        	titulo: anuncio.titulo,
	        	quantia: anuncio.quantia,
	        	tipo: anuncio.tipo,
	        	id: anuncio._id
	        }
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
});