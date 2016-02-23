(function () {
angular
    .module('userApp')
    .factory('userService', userService);

userService.$inject = ['$http'];
    
function userService($http) {
  return {
      userOne: userOne,
    
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
   
} ;

})() ;