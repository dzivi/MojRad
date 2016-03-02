(function ( ){
    angular
    .module("userApp")
    .controller("epController", epController);
    
    epController.$inject = ["$location", "$routeParams", "userService"];
    
    function epController($location, $routeParams, userService) {
        var vm = this;
        
        
        userService.userEP(vm.user)
         .then(function(data){
             vm.user = data;
        
    });
    };
 })();