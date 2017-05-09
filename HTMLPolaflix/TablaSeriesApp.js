angular.module('TablaSeriesApp',[]).
  controller('TablaSeriesController',['$scope','$http',
             function($scope,$http) {

				$scope.seriesEmpezadas      = [];
				$scope.seriesPendientes     = [];
        $scope.seriesTerminadas     = [];
        $scope.dataLoadedEmpezadas  = false;
        $scope.dataLoadedPendientes = false;
        $scope.dataLoadedTerminadas = false;

				$http.get('jsonTest/seriesEmpezadas.json').then(
					function(response) {
            $scope.seriesEmpezadas = response.data;
						$scope.dataLoadedEmpezadas = true;
					},
					function(response) {
						$scope.dataLoadedEmpezadas = false;
					}
				);

        $http.get('jsonTest/seriesPendientes.json').then(
          function(response) {
            $scope.seriesPendientes = response.data;
            $scope.dataLoadedPendientes = true;
          },
          function(response) {
            $scope.dataLoadedPendientes = false;
          }
        );

        $http.get('jsonTest/seriesTerminadas.json').then(
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