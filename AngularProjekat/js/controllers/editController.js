(function(){
angular
    .module('userApp')
    .controller('editlController', editController);
editController.$inject = ["$location", "userService", "$routeParams"];

function editController($location, userService, $routeParams){
    var vm = this;
             
     $http.put('http://localhost:8080/MojRad/webapi/users/').success(function (data) {
        vm.users = data;
    });
     
    
    vm.cancel = cancel;
    vm.edit = edit;
    
    function cancel(){
    $location.path("/users/:userId");  
    };
    
     function edit(){

    $location.path("/users/edit");  
    };
    
    
    userService.userOne($routeParams.userId)
      .then(function(data){
         vm.user1 = data;
          
      });
    };
    
    
    
   
    
})();