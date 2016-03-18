(function () {
    angular
        .module('userApp')
        .config(config);



    function config($routeProvider) {
        $routeProvider

            .when("/login", {
                templateUrl: "logIn.html",
                controller: "logInController",
                controllerAs: "vm"

            })
            .when("/home/:userId", {
                templateUrl: "home.html",
                controller: "homeController",
                controllerAs: "vm"
            })
            .when("/users", {
                templateUrl: "view-list.html",
                controller: "userListController",
                controllerAs: "vm"
            })
            .when("/profile/:userId", {
                templateUrl: "profile.html",
                controller: "eddelController",
                controllerAs: "vm"

            })
            /*
               .when("/profile/edit/:userId",{
                   templateUrl:"view-detail.html",
                   controller:"eddelController",
                   controllerAs: "vm"
                     
                     })
               /*.when("/register",{
                   templateUrl:"view.html",
                   controller:"addController",
                   controllerAs:"vm"
                     
                     })*/
            .otherwise({
                redirectTo: "/login"
            })
    

/*

        $httpProvider.interceptors.push(httpInterceptor);
        httpInterceptor.$inject = ['$q', '$location', 'sessonFactory'];

        function httpInterceptor($q, $location) {
            var vm = this;

            return {
                'request': request,
                'responseError': responseError
            }

            function request(config) {
                config.headers = config.headers || {};
                if (sessonFactory.get('token')) {
                    config.headers.Authorization = 'Bearer ' + sessonFactory.get('token');
                }
                return config;

            }

            function responseError(rejection) {
                if (rejection.status === 401 || rejection.status === 403) {
                    sessonFactory.destroy('token');
                    $location.path('/login');
                }
                return $q.reject(rejection);

            }
        }*/
    };
 })();