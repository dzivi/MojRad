(function(){
    angular
    .module("userApp")
    .run(run);
    
    run.$inject =['$rootScope', '$location', 'LogInService', '$routeParams', 'userService', 'sessonFactory'];
    
    function run($rootScope, $location, LogInService, $routeParams, userService, sessonFactory){
        var vm = this;
       /* vm.userId =userService.userOne();
        console.log(userId);*/
        vm.token = localStorage.getItem('token');
        /*
        var routespermission = [localStorage.getItem('token')];*/
        $rootScope.$on('$routeChangeStart',routeChangeStart);
        
        function routeChangeStart(){
            
            console.log(localStorage.getItem('token'));
        console.log(LogInService.islogged());
            
              if ((localStorage.getItem('token'))!=-1) {
                  
                $location.path("/login");
              }
        }
    }
})();