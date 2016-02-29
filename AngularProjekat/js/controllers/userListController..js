(function(){
angular
    .module('userApp')
    .controller('userListController',userListController);
    userListController. $inject = ["$http", "$location"];
function userListController($http, $location){
    var vm = this;
    
     $http.get('http://localhost:8080/MojRad/webapi/users/').success(function (data) {
        vm.users = data;
    });
    
    vm.eddeluser = function(x){
      $location.path("/users/" + x);
    };
    
  
    
 };
    
})();