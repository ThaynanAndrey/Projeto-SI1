angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state, $mdSidenav) {

	const rotaDePegarAnuncios = "/usuario/listar/anuncios/comprar";
	const rotaDonoDoAnuncio = "/usuario/dono/anuncio/";
	const rotaDecomprarAnuncio = "/usuario/comprar/anuncio";

	$scope.anuncios = [];

	$scope.tiposDeAnuncioStatus = {'movel':true,'imovel':true,'emprego':true,'servico':true};
	$scope.tiposDeAnuncio = ['movel','imovel','emprego','servico'];

	$scope.tipoDeFiltragemSelecionado = 'Tipo';
	$scope.tiposDeFiltragem = ['Data','Tipo'];

	pegarAnuncios();

	function pegarAnuncios() {
		RestService.find(rotaDePegarAnuncios, function(response) {
			$scope.anuncios = construcaoDeObjDeAnuncio(response.data);
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

	$scope.anunciosIsEmpty = function() {
		return $scope.anuncios.length === 0;
	};

	$scope.pegarDono = function(id) {
		RestService.find(rotaDonoDoAnuncio + id, function(response) {
			$scope.donoDoAnuncio = response.data.nome;
		});
	};
	
	$scope.comprarAnuncio = function(anuncio) {
		anuncioComprado = {
	        	titulo: anuncio.titulo,
	        	quantia: anuncio.quantia,
	        	tipo: anuncio.tipo,
	        	id: anuncio._id
	        }; 
		
		if(anuncio.tipo=="servico"){
			anuncioComprado.dataDeAgendamento = anuncio.dataDeAgendamento;
		}

		RestService.add(rotaDecomprarAnuncio, anuncioComprado, function(response) {
			$state.go("home");	
		});
	};

	$scope.validarPeriodo = function(anuncio){
		if($scope.inicioDataDeFiltragem && $scope.fimDataDeFiltragem){
			var dataDeCriacao = anuncio.dataDeCriacao;
			var inicio = $scope.inicioDataDeFiltragem.setHours(0,0,0,0);
			var fim = $scope.fimDataDeFiltragem.setHours(23,59,59,999);
			return dataDeCriacao>=inicio && dataDeCriacao<=fim;
		}
		else{
			return true;
		}
	};
	
	function quatiaAnuncioPorTipo(tipo) {
		if(tipo === "emprego")
			return "Salário";

		return "Preço";
	}

	function compraAnuncioPorTipo(tipo) {
		if(tipo === "emprego")
			return "Solicitar Vaga";

		else if(tipo === "servico")
			return "Agendar";

		return "Comprar";
	}

	$scope.apresentarSideNav = function(anuncio) {
		$scope.anuncioSelecionado = anuncio;
		$scope.anuncioQuantia = quatiaAnuncioPorTipo(anuncio.tipo);
		$scope.anuncioAdiquirir = compraAnuncioPorTipo(anuncio.tipo);
		$scope.pegarDono(anuncio._id);
		$mdSidenav('anuncioInfo').toggle();
	};
	
});