angular.module("adExtreme")

.controller("anunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaListarAnuncios = "/usuario/listar/anuncios";
	const rotaPegarUsuarioLogado = "/usuario/usuarioLogado";
	const rotaAnunciosDeUsuarioLogado = "/usuario/logado/anuncios";
	const rotaPegarMeusAnuncios = "/usuario/logado/anuncios";

	pegarUsuario();
	pegarMeusAnuncios();
	
	$scope.testar = function() {
		
		RestService.find(rotaListarAnuncios, function(response) {
			console.log(response);
		});
	};
	
	function pegarMeusAnuncios() {
		
		RestService.find(rotaPegarMeusAnuncios, function(response) {
			$scope.meusAnuncios = response.data;
		});
	};
	
	function pegarUsuario() {
	
		RestService.find(rotaPegarUsuarioLogado, function(response) {
			$scope.usuarioLogado = response.data;
			$scope.saldoUsuario = $scope.usuarioLogado.saldoCredor + $scope.usuarioLogado.saldoDevedor;
			$scope.saldoDevedor = - $scope.usuarioLogado.saldoDevedor;
			$scope.saldoCredor = $scope.usuarioLogado.saldoCredor;
			
			consertarNumerosFlutuantes();
		});
	};
	
	function isDecimal(numero) {

		if(isNaN(numero)) 
			return false;
		
		return parseInt(numero) != parseFloat(numero);
	};
	
	function consertarNumerosFlutuantes() {
		
		if(isDecimal($scope.saldoUsuario)) {
			$scope.saldoUsuario = $scope.saldoUsuario.toFixed(2);
		}
		
		if(isDecimal($scope.saldoDevedor)) {
			$scope.saldoDevedor = $scope.saldoDevedor.toFixed(2);
		}
		
		if(isDecimal($scope.saldoCredor)) {
			$scope.saldoCredor = $scope.saldoCredor.toFixed(2);
		}
	}
	
	$scope.meusAnunciosEstaVazio = function() {
		return $scope.meusAnuncios.length == 0;
	};
	
	$scope.meusAnuncios = function() {
		
		RestService.find(rotaAnunciosDeUsuarioLogado, function(response) {
			console.log(response);
		});	
	};
	
	$scope.criarNovoAnuncio = function() {
		$state.go("cadastrarAnuncio");		
	};
	
});