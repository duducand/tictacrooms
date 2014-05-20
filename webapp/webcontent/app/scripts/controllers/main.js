'use strict';

angular.module('webappApp').controller('datesPickerCtrl', function ($scope, $http) {
    $scope.today = function () {
        $scope.dat1 = new Date();
        $scope.dat2 = new Date();
    };
    $scope.today();

    $scope.showWeeks = true;
    $scope.toggleWeeks = function () {
        $scope.showWeeks = !$scope.showWeeks;
    };

    $scope.search = function () {
        $http({
            method: 'GET',
            url: 'http://rest-service.guides.spring.io/greeting'
        }).success(function (data) {
            $scope.result = data;
        });
    };

});
