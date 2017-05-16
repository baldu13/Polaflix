angular.module('TempSerieApp',[]).
  controller('TempSerieController',['$scope','$http',
             function($scope,$http) {

				$scope.serie;
        $scope.serieLoaded  = false;

        var idSerie = getParameterByName("id");
        var temp = getParameterByName("temp");

				$http.get('http://localhost:8080/usuarios/gryphus/empezadas.json').then(
					function(response) {
            $scope.serie = response.data;
						$scope.serieLoaded = true;
					},
					function(response) {
						$scope.serieLoaded = false;
					}
				);


          //Funcion para obtener parametros de la url
        function getParameterByName(name, url) {
          if (!url) url = window.location.href;
          name = name.replace(/[\[\]]/g, "\\$&");
          var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
          results = regex.exec(url);
          if (!results) return null;
          if (!results[2]) return '';
          return decodeURIComponent(results[2].replace(/\+/g, " "));
}

        };
      }
    ]);