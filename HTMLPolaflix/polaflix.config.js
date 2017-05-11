angular.
  module('polaflix',['ngRoute','TablaSeriesApp']).
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
          when('/secondParagraph',{
            templateUrl: 'secondParagraph/secondParagraph.html'
          }).
          when('/thirdParagraph',{
            templateUrl: 'thirdParagraph/thirdParagraph.html'
          }).
          //otherwise('/firstParagraph');
          otherwise({
            template : '<div> Ruta no disponible </div>'
          });
      }
    ]);
