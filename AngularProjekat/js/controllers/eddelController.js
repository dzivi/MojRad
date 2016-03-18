(function () {
    angular
        .module('userApp')
        .controller('eddelController', eddelController);
    eddelController.$inject = ["$location", "userService", "$routeParams", "LogInService"];

    function eddelController($location, userService, $routeParams, LogInService) {
        var vm = this;

        vm.nesto = false;

        vm.toggle = function () {
            vm.nesto = !vm.nesto;
        };

        vm.cancel = cancel;

        vm.logout = logout;

        vm.delete = delet;

        vm.save = save;

        vm.home = home;
        

        function home() {
            $location.path("/home/" + $routeParams.userId);
        };
       /*
        vm.edit = function (userId) {
            $location.path("/profile/edit/" + $routeParams.userId)
        };*/

        function cancel() {
            $location.path("/profile/" + $routeParams.userId);
        };

        function logout() {
            LogInService.logout()

        };



        function delet() {
            userService.userDelete($routeParams.userId)
                .then(function (data) {
                    vm.user = data;
                    $location.path("/users");
                });
        };

        userService.userOne($routeParams.userId)
            .then(function (data) {
                vm.user = data;

            });



        function save() {
            userService.userEdit(vm.user)
                .then(function (data) {
                    vm.user = data;
                
                   

                });




        };


    };



})();