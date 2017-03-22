angular.module("adExtreme")

.controller("notificacoesCtrl", function($scope, RestService) {

	const rotaPegarNotificacoesUsuarioLogado = "/usuario/listar/minhas/notificacao";

	$scope.notificacoes = [];

	function pegarNotificoes() {
		RestService.find(rotaPegarNotificacoesUsuarioLogado, function(response) {
			$scope.notificacoes = construcaoDeObjDeNotificacao(response.data);
		});
	};

	function construcaoDeObjDeNotificacao(notificacoes){
		var notificacoesAtualizadas = [];
		notificacoes.forEach(function(notificacao){
			notificacao.dataDeNotificacaoFormatada = new Date(notificacao.dataDeNotificacao).toLocaleString().split(" ")[0];
			notificacoesAtualizadas.push(notificacao);
		});
		return notificacoesAtualizadas;
	}

	$scope.notificacoesIsEmpty = function() {
		return $scope.notificacoes.length == 0;
	}

	pegarNotificoes();
});
