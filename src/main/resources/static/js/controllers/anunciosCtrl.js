angular.module("adExtreme")

.controller("anunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaListarAnuncios = "/usuario/listar/anuncios";
	const rotaPegarUsuarioLogado = "/usuario/usuarioLogado";
	const rotaAnunciosDeUsuarioLogado = "/usuario/logado/anuncios"

	pegarUsuario();
		
	$scope.testar = function() {
		
		RestService.find(rotaListarAnuncios, function(response) {
			console.log(response);
		});
	};
	
	function pegarUsuario() {
	
		RestService.find(rotaPegarUsuarioLogado, function(response) {
			//console.log(response);
			$scope.usuarioLogado = response.data;
			$scope.saldoUsuario = $scope.usuarioLogado.saldoCredor + $scope.usuarioLogado.saldoDevedor;
			$scope.saldoDevedor = - $scope.usuarioLogado.saldoDevedor;
			console.log($scope.usuarioLogado);
		});
	};
	
	$scope.meusAnuncios = function() {
		
		RestService.find(rotaAnunciosDeUsuarioLogado, function(response) {
			console.log(response);
		});	
	};
});