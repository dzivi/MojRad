angular
      .module('userApp')
      .config(config);

function config ($routeProvider){
   $routeProvider
    .when("/",{
       templateUrl:"logIn.html",
       controller:"logInController",
       controllerAs:"vm"
         
         })
    .when("/users",{
       templateUrl:"view-list.html",
       controller:"userListController",
       controllerAs: "vm"
   })
   .when("/users/:userId", {
       resolve:{
           "check": function($location, $rootScope){
               if(!$rootScope.loggedIn){
                   $location.path("/");
               }
           }
       },
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
      redirectTo:"/" 
   });

};