(function () {
angular
    .module('userApp')
    .factory('userService', userService);

userService.$inject = ['$http','$location', '$routeParams'];
    
function userService($http,$location, $routeParams ) {
    var vm = this;
  return {
      userOne: userOne,
      userDelete: userDelete,
      userEdit: userEdit,
      userAdd: userAdd,
      userLogin: userLogin
     
    
  };  
   
    function userOne(data) {
        return $http.get('http://localhost:8080/MojRad/webapi/users/' + data)
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
    
    function userAdd(data){
        return $http.post('http://localhost:8080/MojRad/webapi/users/add', data)
        .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response){
            return response.data;
        };
        
        function getFailed(){
          console.log("Greska")
        };
    };
    
    function userLogin(data){
        console.log(data);
      return $http.post('http://localhost:8080/MojRad/webapi/users',data)
        .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response){
            console.log (response.data)
         if (response.data == "Greska") {
             vm.message = "Not found user";
             return vm.message;
            
              
         }else {
             
              
             $location.path("/users/"+ response.data );
         }
        };
        
       function getFailed(){
        console.log("Greska")
       };
    };
    

} ;

})() ;