(function () {
    angular
        .module('userApp')
        .controller('homeController', homeController);

    homeController.$inject = ['$location', 'userService', 'LogInService', '$routeParams'];

    function homeController($location, userService, LogInService, $routeParams) {
        var vm = this;
        
        
   

        vm.logout = logout;

        vm.profile = profile;

        var userId = $routeParams.userId;

        console.log(userId);

        activated(userId);

        function activated(userId) {
            return userOne(userId).then(function () {

            });
        };

        function userOne(userId) {
            return userService.userOne(userId)
                .then(function (data) {

                });
        }

        userService.userOne($routeParams.userId)
            .then(function (data) {
                vm.user = data;


            });

        function logout() {
            LogInService.logout()
        };

        function profile() {
            userService.userOne($routeParams.userId)
                .then(function (data) {
                    vm.user = data;
                    $location.path("/profile/" + $routeParams.userId);

                });
        };
    };

})();