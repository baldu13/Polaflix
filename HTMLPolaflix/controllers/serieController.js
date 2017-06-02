//Controlador de la serie
angular.module('PolaflixApp').controller('TempSerieController',['$scope','$http',
         function($scope,$http) {

    $scope.todo;
    $scope.serie;
    $scope.capsVistos = [];
    $scope.descripciones = [];
    $scope.serieLoaded  = false;

    var idSerie = getParameterByName("id");
    var temp = getParameterByName("temp");
    $scope.tempActiva;

    $http.get('http://localhost:8080/usuarios/gryphus/empezadas/'+idSerie+'.json').then(
      function(response) {
        $scope.todo = response.data;
        $scope.serie = response.data.serie;
        if($scope.serie){
          for(i=0; i<$scope.serie.temporadas.length; i++){
            if(!$scope.serieLoaded && $scope.serie.temporadas[i].numTemp == temp){
              $scope.tempActiva = $scope.serie.temporadas[i];
              $scope.serieLoaded = true;
              calculaCapsVistos();
            }
          }
        } else {
          $http.get('http://localhost:8080/series/'+idSerie+'.json').then(
            function(response) {
              $scope.todo = response.data;
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

    function calculaCapsVistos() {
      $scope.capsVistos = [];
      var capsTemp = $scope.todo.serie.temporadas[i].capitulos;
      var capsVistos = $scope.todo.capitulos;
      var visto;
      for(i=0;i<capsTemp.length;i++){
        visto = false;
        for(j=0;j<capsVistos.length;j++){
          if(capsVistos[j].id == capsTemp[i].id){
            visto = true;
          }
        }
        $scope.capsVistos.push(visto);
      }
    }

    this.desplegarDescripcion = function(id){
      $scope.descripciones[id-1] = !($scope.descripciones[id-1]); //Invertimos, si se mostraba se deja de mostrar y viceversa
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