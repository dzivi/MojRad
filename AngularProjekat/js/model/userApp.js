var app = angular.module("userApp",["ngRoute"]);

app.config(function($routeProvider){
   $routeProvider
    .when("/users",{
       templateUrl:"view-list.html",
       controller:"userListController"
   })
   .when("/users/:userId",{
       templateUrl:"view-list1.html",
       controller:"eddelController"
   })
   .otherwise({
      redirectTo:"/users" 
   });

});