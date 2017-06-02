//Controlador para la visualizacion del cap
angular.module('PolaflixApp').controller('CapSerieController',['$scope','$http',
         function($scope,$http) {

    $scope.serie = getParameterByName("serie");
    $scope.temp = getParameterByName("temp");
    $scope.cap = getParameterByName("cap");

    //Funcion para obtener parametros de la url
    function getParameterByName(name, url) {
      if (!url) url = window.location.href;
      name = name.replace(/[\[\]]/g, "\\$&");
      var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
      results = regex.exec(url);
      if (!results) return null;
      if (!results[2]) return '';
      return decodeURIComponent(results[2].replace(/\+/g, " "));
    };
  }
]);