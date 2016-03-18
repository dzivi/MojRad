(function () {
angular
    .module('userApp')
    .factory('userService', userService);

userService.$inject = ['$http','$location', '$routeParams'];
    
function userService($http,$location, $routeParams, $rootScope, sessonFactory ) {
    var vm = this;
  return {
      userOne: userOne,
      userDelete: userDelete,
      userEdit: userEdit,
      userAdd: userAdd
     
     
    
  };  
   
    function userOne(data) {
        console.log(data);
        return $http.get('http://localhost:8086/MojRad/webapi/users/' + data)
        .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response) {
          return response.data;  
        } ;
        function getFailed() {
          console.log("Greska")  
        } ;
    } ;
    
    function userDelete(data){
      return $http.delete('http://localhost:8086/MojRad/webapi/users/' + data)
      .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response) {
          return response.data;  
        } ;
        function getFailed() {
          console.log("Greska")  
        } ;
    };
    
    function userEdit(data){
      return $http.put('http://localhost:8086/MojRad/webapi/users/',data)
       .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response) {
          return response.data;  
        } ;
        function getFailed() {
          console.log("Greska")  
        } ;
    };
    
    function userAdd(data){
        return $http.post('http://localhost:8086/MojRad/webapi/users/add', data)
        .then(getComplete)
        .catch(getFailed);
        
        function getComplete(){
            
            vm.poruka = "Successful Registration!!";
             return vm.poruka;
            
        };
        
        function getFailed(){
            console.log("Greska")
             vm.opa = "Registration Failed!!";
             return vm.opa;
          
        };
    };
    
  
    

} ;

})() ;