//Controlador para el listado de series
angular.module('PolaflixApp').controller('ListadoSeriesController',['$scope','$http',
         function($scope,$http) {

    $scope.letrasFiltradas = [];
    $scope.textoFiltrado;
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
        $scope.series = $scope.seriesTotal;//.splice(0);
      } else {
        for(i=0;i<$scope.seriesTotal.length; i++){
          letra = $scope.seriesTotal[i].nombre.charAt(0); //Inicial
          if($scope.letrasFiltradas.indexOf(letra)!=-1){
            $scope.series.push($scope.seriesTotal[i]);
          }
        }
      }
      console.log($scope.series);
      for(i=0;i<$scope.series.length;i++){
        if(!contieneCadena($scope.textoFiltrado,$scope.series[i])){
          //Eliminarla
          console.log($scope.series[i]);
        }
      }
    }

    this.estaFiltrada = function(letra){
      return $scope.letrasFiltradas.indexOf(letra)!=-1;
    }

    this.filtraBusqueda = function() {
      $scope.textoFiltrado = $scope.searchText;
      this.recalculaListaSeries();
    }

    function contieneCadena(cadena, serie){
      var txt = cadena.toLowerCase();
      var titulo = serie.nombre.toLowerCase();
      return titulo.indexOf(txt)!=-1;
    }
  }
]);