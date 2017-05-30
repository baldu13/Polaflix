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
          window.location.replace('http://localhost:8000/polaflix.html#!/verSerie?serie='+$scope.serie.nombre+'&temp='+temp+'&cap='+cap);
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


    //Controlador para los cargos
    myApp.controller('CargosController',['$scope','$http',
             function($scope,$http) {

        $scope.mes = getParameterByName("mes");
        $scope.anyo = getParameterByName("anyo");
        $scope.cargos;
        $scope.total = 0;
        $scope.loaded = false;
        $scope.meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];

        var d = new Date();

        if(!$scope.mes) $scope.mes = d.getMonth()+1;
        if(!$scope.anyo) $scope.anyo = d.getFullYear();

        $http.get('http://localhost:8080/usuarios/gryphus/cargos/'+$scope.anyo+'/'+$scope.mes+'.json').then(
          function(response) {
            $scope.cargos = response.data.capitulos;
            var i;
            if(response.data){
              for (i = 0; i < response.data.capitulos.length; i++) {
                  $scope.total += response.data.capitulos[i].precio;
              }
            }
            calculaTotal();
            $scope.loaded = true;
          },
          function(response) {
            $scope.loaded = false;
          }
        );

        
        //Cambiar a mes anterior o siguiente en la factura
        this.changePeriodoClicked = function(tmp){
          if(tmp == 2){ //Mes anterior
            if($scope.mes == 1){
              $scope.mes = 12;
              $scope.anyo = $scope.anyo - 1;
            }else {
              $scope.mes--;
            }
          } else if(tmp ==1){
            if($scope.mes == 12){
              $scope.mes = 1;
              $scope.anyo++;
            } else {
              $scope.mes++;
            }
          }
          window.location.replace('http://localhost:8000/polaflix.html#!/cargos?mes='+$scope.mes+'&anyo='+$scope.anyo);
        }

        var calculaTotal =function(){
          $http.get('http://localhost:8080/usuarios/gryphus/cuotaMax').then(
          function(response) {
            var cuota = response.data;
            if(cuota != -1){
              $scope.total = cuota;
            }
          },
          function(response) {
          }
          );
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

    //Controlador para el listado de series
    myApp.controller('ListadoSeriesController',['$scope','$http',
             function($scope,$http) {

        $scope.letrasFiltradas = [];
        $scope.seriesTotal;
        $scope.series;
        $scope.descripciones = [];
        $scope.loaded = false;

        $http.get('http://localhost:8080/series.json').then(
          function(response) {
            $scope.series = response.data;
            $scope.seriesTotal = response.data;
            for (i = 0; i < response.data.length; i++) {
                  $scope.descripciones.push(false); //No se muestra ninguna al principio
            }
            $scope.loaded = true;
          },
          function(response) {
            $scope.loaded = false;
          }
        );

        this.anadirSerie = function(id){
          $http.post('http://localhost:8080/usuarios/gryphus/empezadas/'+id);
        }

        this.desplegarDescripcion = function(id){
          $scope.descripciones[id-1] = !($scope.descripciones[id-1]); //Invertimos, si se mostraba se deja de mostrar y viceversa
        }

        this.filtrar = function(letra){
          var index = $scope.letrasFiltradas.indexOf(letra);
          if(index==-1){
            //Anadimos la letra al filtro
            $scope.letrasFiltradas.push(letra);
          } else {
            $scope.letrasFiltradas.splice(index, 1);
          }
          this.recalculaListaSeries();
        }

        this.recalculaListaSeries = function(){
          $scope.series = [];
          var letra;
          if($scope.letrasFiltradas.length == 0){
            $scope.series = $scope.seriesTotal;
          } else {
            for(i=0;i<$scope.seriesTotal.length; i++){
              letra = $scope.seriesTotal[i].nombre.charAt(0); //Inicial
              if($scope.letrasFiltradas.indexOf(letra)!=-1){
                $scope.series.push($scope.seriesTotal[i]);
              }
            }
          }
        }

        this.estaFiltrada = function(letra){
          return $scope.letrasFiltradas.indexOf(letra)!=-1;
        }
      }
    ]);