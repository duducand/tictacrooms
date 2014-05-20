'use strict';

angular.module('webappApp', ['ngCookies', 'ngResource', 'ngSanitize', 'ngRoute', 'ui.bootstrap']).config(function ($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'views/main.html',
            controller: 'datesPickerCtrl'
        }).when('/search', {
            templateUrl: 'views/search.html',
            controller: 'SearchCtrl'
        }).when('/announce', {
            templateUrl: 'views/announce.html',
            controller: 'SearchCtrl'
        }).otherwise({
            redirectTo: '/'
        });
});
