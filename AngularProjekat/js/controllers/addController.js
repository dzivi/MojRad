(function(){
    angular
    .module("userApp")
    .controller("addController", addController);
    
    addController.$inject = ["$location", "$routeParams", "userService"];
    
    function addController($location, $routeParams, userService){
       var vm = this;
    
 
        
        vm.cancel =  cancel;
        vm.save = save;
        
        function save(){
          userService.userAdd(vm.user)
          .then(function(data){
             vm.user = data;
          $location.path("/login");
          });
        };
        
        function cancel(){
             $location.path("/login");
        };
    };
    
 })();