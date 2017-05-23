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
          if((temp>1 && tmp == 2) || (temp < $scope.serie.temporadas.length && tmp == 1)){ //En los limites de las temporadas
            if(tmp == 1)
              temp = Number(temp) + 1;
            if(tmp == 2)
              temp = Number(temp) - 1;
            window.location.replace('http://localhost:8000/polaflix.html#!/serie?id='+idSerie+'&temp='+temp);
          }
        }

        this.seeCap = function(serie, temp, cap){
          $http.post('http://localhost:8080/usuarios/gryphus/capitulosVistos/'+serie+'/'+temp+'/'+cap);
          window.location.replace('http://localhost:8000/polaflix.html#!/verSerie?id='+serie+'&temp='+temp+'&cap='+cap);
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


    //Controlador para la visualizacion del cap
    myApp.controller('CapSerieController',['$scope','$http',
             function($scope,$http) {

        $scope.serie = getParameterByName("id");
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


    //Controlador para los cargos
    myApp.controller('CargosController',['$scope','$http',
             function($scope,$http) {

        $scope.mes = getParameterByName("mes");
        $scope.anyo = getParameterByName("anyo");
        $scope.cargos;
        $scope.total = 0;
        $scope.loaded = false;

        var d = new Date();

        if(!$scope.mes) $scope.mes = d.getMonth()+1;
        if(!$scope.anyo) $scope.anyo = d.getFullYear();

        $http.get('http://localhost:8080/usuarios/gryphus/cargos/'+$scope.anyo+'/'+$scope.mes+'.json').then(
          function(response) {
            console.log('Cargos del mes/anyo: '+$scope.mes+'/'+$scope.anyo);
            $scope.cargos = response.data.capitulos;
            console.log(response.data);
            var i;
            if(response.data){
              for (i = 0; i < response.data.capitulos.length; i++) {
                  $scope.total += response.data.capitulos[i].precio;
              }
              $scope.loaded = true;
            }
          },
          function(response) {
            $scope.loaded = false;
          }
        );


        //Cambiar a mes anterior o siguiente en la factura
        //TODO
        this.changePeriodoClicked = function(tmp){
          if((temp>1 && tmp == 2) || (temp < $scope.serie.temporadas.length && tmp == 1)){ //En los limites de las temporadas
            if(tmp == 1)
              temp = Number(temp) + 1;
            if(tmp == 2)
              temp = Number(temp) - 1;
            window.location.replace('http://localhost:8000/polaflix.html#!/serie?id='+idSerie+'&temp='+temp);
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