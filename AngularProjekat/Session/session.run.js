(function(){
    angular
    .module("userApp")
    .run(run);
    
    run.$inject =['$rootScope', '$location', 'LogInService', '$routeParams'];
    
    function run($rootScope, $location, LogInService, $routeParams){
        
        var routespermission = ["/users/:userId"];
        $rootScope.$on('$routeChangeStart',routeChangeStart);
        
        function routeChangeStart(){
              if (routespermission.indexOf($location.path()) !=-1 && !LogInService.islogged()) {
                $location.path("/login");
            }
        }
    }
})();