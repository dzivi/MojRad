(function(){
    angular
    .module("userApp")
    .factory("LogInService", LogInService);
    
    LogInService.$inject = ['$http','$location', '$routeParams','$rootScope', 'sessonFactory'];
    
    var vm = this;
    
    function LogInService($http, $location, $routeParams,$rootScope, sessonFactory){
        return{
            
            logIn:logIn,
            logout:logout,
            islogged:islogged

        };
          function logIn(data){
        console.log(data);
      return $http.post('http://localhost:8080/MojRad/webapi/users',data)
        .then(getComplete)
        .catch(getFailed);
        
        function getComplete(response){
            console.log (response.data)
         if (response.data.ID == null) {
             alert('Wrong email or password');
             vm.message = "Not found user";
             return vm.message;
            
              
         }else { 
             sessonFactory.set('token', response.data.ID);
             $rootScope.loggedIn = true;
             $location.path("/users/"+ response.data.ID );
         }
        };
        
       function getFailed(){
        console.log("Greska")
       };
    };
        function logout(){
            sessonFactory.destroy('token');
          $location.path("/");  
        };
        
        function islogged(){
            sessonFactory.get('token');
            $rootScope.islogged = false;
            $location.path("/");
           /* if(sessonFactory.get('token')){
                return true;
            }else{
                return false;
            }*/
        };
};
})();