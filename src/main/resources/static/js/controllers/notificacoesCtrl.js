angular.module("adExtreme")

.controller("notificacoesCtrl", function($scope) {

	const rotaPegarNotificacoesUsuarioLogado = "/usuario/listar/minhas/notificacao";

	$scope.notificacoes = [];

	function pegarNotificoes() {
		RestService.find(rotaPegarNotificacoesUsuarioLogado, function(response) {
			$scope.notificacoes = response.data;
		});
	};

	$scope.notificacoesIsEmpty = function() {
		return $scope.notificacoes.length === 0;
	}
});
