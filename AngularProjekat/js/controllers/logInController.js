(function () {
    angular
        .module("userApp")
        .controller("logInController", logInController);

    logInController.$inject = ["$location", "LogInService"];

    function logInController($location, LogInService) {
        var vm = this;

        vm.logIn = logIn;
        vm.message = " ";

        vm.save = save;

        function save() {
            userService.userAdd(vm.user)
                .then(function (data) {
                    vm.user = data;
                    $location.path("/login");
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