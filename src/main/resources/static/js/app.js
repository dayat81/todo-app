
var app = angular.module('TODOApp',['ui.router','ngStorage','ui.bootstrap.datetimepicker','ui.dateTimeInput']);

app.constant('urls', {
    BASE: 'http://localhost:8080/TODOApp',
    TASK_SERVICE_API : 'http://localhost:8080/TODOApp/api/task/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'template/tasksList',
                controller:'TaskController',
                controllerAs:'taskCtrl',
                resolve: {
                    users: function ($q, TaskService) {
                        console.log('Loading all tasks');
                        var deferred = $q.defer();
                        TaskService.loadAllTasks().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

