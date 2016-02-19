app.controller('userListController', ["$scope", "$http", "$location", function ($scope, $http, $location) {
    $http.get('http://localhost:8080/MojRad/webapi/users/').success(function (data) {
        $scope.users = data;
    });
    
    $scope.eddeluser = function(x){
      $location.path("/users/" + x);
    };
    
} ]);