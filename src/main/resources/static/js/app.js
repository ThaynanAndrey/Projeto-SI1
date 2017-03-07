angular.module("adExtreme", ['ui.router', 'ngMaterial', 'ngMessages']) 

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
    
    $urlRouterProvider.otherwise("/");
     
    $stateProvider
            
            .state('home', {
                url: "/",
                templateUrl: "./templates/angular/layout_user.html",
                controller: "anunciosCtrl"
            })

            .state('cadastrarAnuncio', {
                url: "/cadastrarAnuncio",
                templateUrl: "./templates/angular/cadastrar_anuncio.html",
                controller: "cadastrarAnuncioCtrl"
            })

            .state('listarAnuncios', {
                url: "/listarAnuncios",
                templateUrl: "./templates/angular/listar_anuncios.html"
            });
}]);