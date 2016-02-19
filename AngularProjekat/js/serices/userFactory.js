app.factory('userService', ["$http", function($http){
    function eddeluser(x){
      $http.get('http://localhost:8080/MojRad/webapi/users/' + x).success(function(data){
      
          return data;
      
      }); 
        return{
            nesto:eddeluser
        };
    };
}]);