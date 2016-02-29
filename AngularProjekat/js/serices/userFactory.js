(function () {
angular
    .module('userApp')
    .factory('userService', userService);

userService.$inject = ['$http'];
    
function userService($http) {
  return {
      userOne: userOne,
      userDelete: userDelete,
      userEdit: userEdit
      
    
  };  
    
    function userOne(userId) {
        return $http.get('http://localhost:8080/MojRad/webapi/users/' + userId)
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
      return $http.delete('http://localhost:8080/MojRad/webapi/users/' + data)
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
      return $http.put('http://localhost:8080/MojRad/webapi/users/',data)
       .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response) {
          return response.data;  
        } ;
        function getFailed() {
          console.log("Greska")  
        } ;
    };
    

} ;

})() ;