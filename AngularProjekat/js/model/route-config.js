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
   .when("/users/edit/:userId",{
       templateUrl:"view-detail.html",
       controller:"eddelController",
       controllerAs: "vm"
         
         })
   .when("/add/users/",{
       templateUrl:"view.html",
       controller:"addController",
       controllerAs:"vm"
         
         })

   .otherwise({
      redirectTo:"/users" 
   });

};