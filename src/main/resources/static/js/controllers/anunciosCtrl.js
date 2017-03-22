angular.module("adExtreme")

.controller("anunciosCtrl", function($scope, RestService, $state, $http) {

	const rotaListarAnuncios = "/usuario/listar/anuncios";
	const rotaPegarUsuarioLogado = "/usuario/usuarioLogado";
	const rotaAnunciosDeUsuarioLogado = "/usuario/logado/anuncios";
	const rotaPegarMeusAnuncios = "/usuario/logado/anuncios";

	$scope.tiposDeAnuncioStatus = {'movel':true,'imovel':true,'emprego':true,'servico':true};
	$scope.tiposDeAnuncio = ['movel','imovel','emprego','servico'];

	$scope.tipoDeFiltragemSelecionado = 'Tipo';
	$scope.tiposDeFiltragem = ['Data','Tipo'];
	

	pegarUsuario();
	pegarMeusAnuncios();
	
	$scope.testar = function() {
		
		RestService.find(rotaListarAnuncios, function(response) {
			console.log(response);
		});
	};
	
	function pegarMeusAnuncios() {
		RestService.find(rotaPegarMeusAnuncios, function(response) {
			$scope.meusAnuncios = construcaoDeObjDeAnuncio(response.data);
		});
	};

	function construcaoDeObjDeAnuncio(anuncios){
		var anunciosAtualizados = [];
		anuncios.forEach(function(anuncio){
			anuncio.dataDeCriacaoFormatada = new Date(anuncio.dataDeCriacao).toLocaleString().split(" ")[0];
			anunciosAtualizados.push(anuncio);
		});
		return anunciosAtualizados;
	}
	
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

	$scope.validarPeriodo = function(anuncio){
		if($scope.inicioDataDeFiltragem && $scope.fimDataDeFiltragem){
			var dataDeCriacao = anuncio.dataDeCriacao;
			var inicio = $scope.inicioDataDeFiltragem.getTime();
			var fim = $scope.fimDataDeFiltragem.getTime();
			return dataDeCriacao>=inicio && dataDeCriacao<=fim;
		}
		else{
			return true;
		}
	};
});