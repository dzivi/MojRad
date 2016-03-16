angular
      .module('userApp')
      .config(config);

function config ($routeProvider){
   $routeProvider
  
    .when("/login",{
       templateUrl:"logIn.html",
       controller:"logInController",
       controllerAs:"vm"
         
         })
    .when("/home/:userId",{
       templateUrl:"home.html",
       controller:"homeController",
       controllerAs: "vm"
   })
    .when("/users",{
       templateUrl:"view-list.html",
       controller:"userListController",
       controllerAs: "vm"
   })
  .when("/profile/:userId", {
       templateUrl:"view-list1.html",
       controller:"eddelController",
       controllerAs: "vm"

   })
   .when("/profile/edit/:userId",{
       templateUrl:"view-detail.html",
       controller:"eddelController",
       controllerAs: "vm"
         
         })
   .when("/register",{
       templateUrl:"view.html",
       controller:"addController",
       controllerAs:"vm"
         
         })
   .otherwise({
      redirectTo:"/login" 
   });

};