var myApp = angular.module('PolaflixApp',[]);

  //Controlador de la pagina principal
  myApp.controller('TablaSeriesController',['$scope','$http',
             function($scope,$http) {

				$scope.seriesEmpezadas      = [];
				$scope.seriesPendientes     = [];
        $scope.seriesTerminadas     = [];
        $scope.dataLoadedEmpezadas  = false;
        $scope.dataLoadedPendientes = false;
        $scope.dataLoadedTerminadas = false;

				$http.get('http://localhost:8080/usuarios/gryphus/empezadas.json').then(
					function(response) {
            $scope.seriesEmpezadas = response.data;
						$scope.dataLoadedEmpezadas = true;
					},
					function(response) {
						$scope.dataLoadedEmpezadas = false;
					}
				);

        $http.get('http://localhost:8080/usuarios/gryphus/pendientes.json').then(
          function(response) {
            $scope.seriesPendientes = response.data;
            $scope.dataLoadedPendientes = true;
          },
          function(response) {
            $scope.dataLoadedPendientes = false;
          }
        );

        $http.get('http://localhost:8080/usuarios/gryphus/terminadas.json').then(
          function(response) {
            $scope.seriesTerminadas = response.data;
            $scope.dataLoadedTerminadas = true;
          },
          function(response) {
            $scope.dataLoadedTerminadas = false;
          }
        );

        //No utilizado de momento
        this.onOrderingIconClicked = function(column,asc) {
          $scope.orderingCriteria = (asc?"":"-") + column;
        };
      }
    ]);

    //Controlador de la serie
    myApp.controller('TempSerieController',['$scope','$http',
             function($scope,$http) {

        $scope.serie;
        $scope.serieLoaded  = false;

        var idSerie = getParameterByName("id");
        var temp = getParameterByName("temp");
        $scope.tempActiva;

        $http.get('http://localhost:8080/series/'+idSerie+'.json').then(
          function(response) {
            $scope.serie = response.data;
            for(i=0; i<$scope.serie.temporadas.length; i++){
              if(!$scope.serieLoaded && $scope.serie.temporadas[i].numTemp == temp){
                $scope.tempActiva = $scope.serie.temporadas[i];
                $scope.serieLoaded = true;
              }
            }
          },
          function(response) {
            $scope.serieLoaded = false;
          }
        );

        //Funciones para cambiar de temp (adelante y atras)
        this.changeTempClicked = function(tmp){
          console.log($scope.tempActiva.capitulos);
          if((temp>1 && tmp == 2) || (temp < $scope.serie.temporadas.length && tmp == 1)){ //En los limites de las temporadas
            if(tmp == 1)
              temp = Number(temp) + 1;
            if(tmp == 2)
              temp = Number(temp) - 1;
            window.location.replace('http://localhost:8000/paginaPersonal.html#!/serie?id=1&temp='+temp);
          }
        }

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