(function () {
    angular
        .module("userApp")
        .controller("addController", addController);

    addController.$inject = ["$location", "$routeParams", "userService"];

    function addController($location, $routeParams, userService) {
        var vm = this;

        





        vm.save = save;

        function save() {
            userService.userAdd(vm.user)
                .then(function (data) {
                   // vm.user = data;
                    //$location.path("/login");
                vm.poruka = data;
                    return vm.poruka;
                });
        };
    };

})();