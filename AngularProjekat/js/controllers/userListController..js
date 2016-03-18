(function(){
angular
    .module('userApp')
    .controller('userListController',userListController);
    userListController. $inject = ["$http", "$location"];
function userListController($http, $location){
    var vm = this;
    
     $http.get('http://localhost:8086/MojRad/webapi/users/').success(function (data) {
        vm.users = data;
    });
    
    vm.eddeluser = function(userId){
      $location.path("/profile/" + userId);
    };
    

 };
    
})();