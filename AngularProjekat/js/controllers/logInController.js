(function(){
    angular
    .module("userApp")
    .controller("logInController", logInController);
    
    logInController.$inject = ["$location", "LogInService"];
    
    function logInController($location, LogInService){
      var vm = this;
        
        vm.logIn = logIn;
        vm.message = " ";
        
        function logIn(user){
          LogInService.logIn(user)
              .then(function(data){
          vm.message = data;
              return vm.message;
        });
                  
    };
    };
})();