(function(){
angular
    .module('userApp')
    .controller('eddelController', eddelController);
eddelController.$inject = ["$location", "userService", "$routeParams"];

function eddelController($location, userService, $routeParams){
    var vm = this;
    
    vm.cancel = cancel;
   
    function cancel(){
    $location.path("/users");  
    };
    
    userService.userOne($routeParams.userId)
      .then(function(data){
         vm.user1 = data;
          
      });
    };
    
    
    
   
    
})();
