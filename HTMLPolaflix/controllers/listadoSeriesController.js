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
    }

    //Para colorear las letras que estan pintadas
    this.estaFiltrada = function(letra){
      return $scope.letrasFiltradas.includes(letra);
    }


    this.filtraBusqueda = function() {
      return function(serie) {
          if(!$scope.searchText){
            return true;
          }
          var se = serie.nombre.toLowerCase();
          return se.includes($scope.searchText.toLowerCase());
      };
    }

    this.filtraLetras = function() {
      return function(serie) {
          if($scope.letrasFiltradas.length==0){
            return true;
          }
          var character = serie.nombre.charAt(0).toUpperCase();
          return $scope.letrasFiltradas.includes(character);
      };
    }
  }
]);