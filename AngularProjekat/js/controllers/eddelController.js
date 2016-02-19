app.controller('eddelController', ["$scope", "$location", "userService", "$routeParams" function($scope, $location, userService, $routeParams){
 
    $scope.cancel = function(){
      $location.path("/users");  
    };
    
   
        userService.nesto($routeParams.userId).then(function(data){
                                   $scope.user = data;
                                   }, function(error){
                                   console.log(error);
                                   });
    
    
    
    
}]);