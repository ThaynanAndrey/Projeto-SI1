angular.module("adExtreme")

.controller("listarAnunciosCtrl", function($scope, RestService, $state, $mdSidenav) {

	const rotaDePegarAnuncios = "/usuario/listar/anuncios/comprar";
	const rotaDonoDoAnuncio = "/usuario/dono/anuncio/";
	const rotaDecomprarAnuncio = "/usuario/comprar/anuncio";
	const rotaCadastrarNotificacao = "/usuario/cadastrar/notificacao";
	const rotaPegarUsuarioLogado = "/usuario/usuarioLogado";

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
			$scope.donoDoAnuncio = response.data;
		});
	};
	
	$scope.comprarAnuncio = function(anuncio) {
		var anuncioComprado = {
	        	titulo: anuncio.titulo,
	        	quantia: anuncio.quantia,
	        	tipo: anuncio.tipo,
	        	id: anuncio._id
	        }; 
	    var menssagemDeNotificacao;

	    if(anuncio.tipo=="emprego"){
			menssagemDeNotificacao = "Parabéns! O usuário " + $scope.usuarioLogado.nome + " solicitou uma vaga ao emprego " + 
									anuncio.titulo + "."; 
	    }
	    else if(anuncio.tipo=="servico"){
	    	var dataCompleta = (new Date(anuncio.dataDeCriacao).toLocaleString()).split(" ");
	    	var data = dataCompleta[0];
	    	var horario = dataCompleta[1];

	    	menssagemDeNotificacao = "Parabéns! O usuário " + $scope.usuarioLogado.nome + " agendou seu serviço " + 
	    							anuncio.titulo + " para o dia " + data + " às " + horario + ".";
	    }
	    else{
	    	menssagemDeNotificacao = "Parabéns! Seu anúncio " + anuncio.titulo +" foi comprado pelo usuário " + 
	    							$scope.usuarioLogado.nome + ".";
	    }

		var novaNotificacao = {
				descricao: menssagemDeNotificacao,
				dono: {"id": $scope.donoDoAnuncio.id},	
				dataDeNotificacao: new Date().getTime()
		};

		if(anuncio.tipo=="servico"){
			anuncioComprado.dataDeAgendamento = anuncio.dataDeAgendamento;
		}


		RestService.add(rotaCadastrarNotificacao, novaNotificacao, function(data) {
			RestService.add(rotaDecomprarAnuncio, anuncioComprado, function(response) {
				$state.go("home");	
			});
		});
	};

	function pegarUsuario() {
		RestService.find(rotaPegarUsuarioLogado, function(response) {
			$scope.usuarioLogado = response.data;
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
		pegarUsuario();
		$mdSidenav('anuncioInfo').toggle();
	};
	
});