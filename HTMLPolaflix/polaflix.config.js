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
          //otherwise('/firstParagraph');
          otherwise({
            template : '<div> Ruta no disponible </div>'
          });
      }
    ]);
