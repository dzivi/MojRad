angular
      .module('userApp')
      .config(config);

function config ($routeProvider){
   $routeProvider
    .when("/users",{
       templateUrl:"view-list.html",
       controller:"userListController",
       controllerAs: "vm"
   })
   .when("/users/:userId", {
       templateUrl:"view-list1.html",
       controller:"eddelController",
       controllerAs: "vm"

   })
   
    .when("/users/edit", {
       templateUrl:"view-detail.html",
       controller:"editController",
       controllerAs: "vm"

   })

   .otherwise({
      redirectTo:"/users" 
   });

};