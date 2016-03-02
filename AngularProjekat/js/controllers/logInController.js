(function(){
    angular
    .module("userApp")
    .controller("logInController", logInController);
    
    logInController.$inject = ["$location", "userService"];
    
    function logInController($location, userService){
      var vm = this;
        
        vm.logIn = logIn;
        vm.message = " ";
        
        function logIn(data){
          userService.userLogin(data)
              .then(function(data){
          vm.message = data;
              return vm.message;
        });
                  
    };
    };
})();