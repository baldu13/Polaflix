//Controlador para los cargos
angular.module('PolaflixApp').controller('CargosController',['$scope','$http',
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
