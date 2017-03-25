angular.module("adExtreme")

.controller("notificacoesCtrl", function($scope, RestService) {

	const rotaPegarNotificacoesUsuarioLogado = "/usuario/listar/minhas/notificacao";

	$scope.notificacoes = [];

	/**
	 * Metodo que pega todas as notificacoes do usuario logado
	 * @returns Resposta da requisicao HTTP
	 */
	function pegarNotificoes() {
		RestService.find(rotaPegarNotificacoesUsuarioLogado, function(response) {
			$scope.notificacoes = construcaoDeObjDeNotificacao(response.data);
		});
	};

	/**
	 * Metodo de modelacao das notificacoes
	 * @param notificacoes - notificacoes a serem construidas
	 * @returns notificacoes modeladas
	 */
	function construcaoDeObjDeNotificacao(notificacoes){
		var notificacoesAtualizadas = [];
		notificacoes.forEach(function(notificacao){
			notificacao.dataDeNotificacaoFormatada = new Date(notificacao.dataDeNotificacao).toLocaleString().split(" ")[0];
			notificacoesAtualizadas.push(notificacao);
		});
		return notificacoesAtualizadas;
	}

	/**
	 * Retorna um boolean representando se a lista de notificacoes esta vazia
	 * @returns boolean - Se esta sem notificacoes
	 */
	$scope.notificacoesIsEmpty = function() {
		return $scope.notificacoes.length == 0;
	}

	pegarNotificoes();
});
