angular.
  module('polaflix',['ngRoute','PolaflixApp']).
    config(['$locationProvider','$routeProvider',
      function config($locationProvider,$routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
          when('/',{
            templateUrl : 'divs/paginaPersonal.html'
          }).
          when('/serie',{
            templateUrl: 'divs/paginaSerie.html'
          }).
          when('/cargos',{
            templateUrl: 'divs/cargos.html'
          }).
          when('/addSerie',{
            templateUrl: 'divs/addSerie.html'
          }).
          when('/verSerie',{
            templateUrl: 'divs/verSerie.html'
          }).
          //otherwise('/firstParagraph');
          otherwise({
            template : '<div> Ruta no disponible </div>'
          });
      }
    ]);
