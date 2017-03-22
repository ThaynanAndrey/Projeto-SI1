angular.module("adExtreme", ['ui.router', 'ngMaterial', 'ngMessages'])

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
    
    $urlRouterProvider.otherwise("/");
     
    $stateProvider
            
            .state('home', {
                url: "/",
                templateUrl: "../partials/layout_user.html",
                controller: "anunciosCtrl"
            })

            .state('cadastrarAnuncio', {
                url: "/cadastrarAnuncio",
                templateUrl: "../partials/cadastrar_anuncio.html",
                controller: "cadastrarAnuncioCtrl"
            })

            .state('listarAnuncios', {
                url: "/listarAnuncios",
                templateUrl: "../partials/listar_anuncios.html",
                controller: "listarAnunciosCtrl"
            })

            .state('notificacoesUsuario', {
                url: "/minhasNotificacoes",
                templateUrl: "../partials/notificacoes.html",
                controller: "notificacoesCtrl"
            })
            
            .state('perfilPessoal', {
                url: "/meuPerfil",
                templateUrl: "../partials/perfil_pessoal.html",
                controller: "anunciosCtrl"
            });
}]);