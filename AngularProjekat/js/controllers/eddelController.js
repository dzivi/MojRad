(function () {
angular
    .module('userApp')
    .controller('eddelController', eddelController);
eddelController.$inject = ["$location", "userService", "$routeParams"];

function eddelController($location, userService, $routeParams){
    var vm = this;
    
    
    vm.cancel = cancel;
    
    vm.delete = delet;
    
    vm.save = save;
    
    vm.edit = function(userId){
      $location.path("/users/edit/" + $routeParams.userId)  
    };
   
    function cancel(){
    $location.path("/users");  
    };
    
    function delet() {    
    userService.userDelete($routeParams.userId)
      .then(function(data){
         vm.user = data;
          
      });
    };
    
    userService.userOne($routeParams.userId)
      .then(function(data){
         vm.user = data;
          
      });

    
    
    function save(){
        userService.userEdit(vm.user1)
        .then(function(data){
          vm.user = data; 
              $location.path("/users");  
     
    });
    
             
      
    
    };
    
    
};
    
   
    
})() ;
