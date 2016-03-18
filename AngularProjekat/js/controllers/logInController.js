(function () {
    angular
        .module("userApp")
        .controller("logInController", logInController);

    logInController.$inject = ["$location", "LogInService", "userService"];

    function logInController($location, LogInService, userService) {
        var vm = this;

        vm.poruka = " ";

        vm.opa = " ";

        vm.logIn = logIn;

        vm.register = register;

        function register() {
            userService.userAdd(vm.user)
                .then(function (data) {
                    // vm.user = data;
                    //  $location.path("/login");
                    vm.user.firstName = " ";
                    vm.user.lastName = " ";
                    vm.user.email = " ";
                    vm.user.sex = " ";
                    vm.user.password = " ";
                    vm.user.birdthday = " ";
                    vm.user.nickName = " ";

                    vm.poruka = data;
                    return vm.poruka;

                });
        };


        function logIn(user) {
            LogInService.logIn(user)
                .then(function (data) {
                    vm.message = data;
                    return vm.message;
                });

        };
        vm.addUser = function () {
            $location.path("/register")
        };


    };
})();