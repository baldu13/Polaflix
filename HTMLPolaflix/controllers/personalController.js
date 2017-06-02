//Controlador de la pagina principal
angular.module('PolaflixApp').controller('TablaSeriesController',['$scope','$http',
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
