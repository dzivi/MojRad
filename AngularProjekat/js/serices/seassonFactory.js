(function(){
    angular
    .module("userApp")
    .factory("sessonFactory", sessonFactory);
    
    sessonFactory.$inject = ["$http"];
    
    function sessonFactory($http){
        return{
            
            set:set,
            get:get,
            destroy:destroy
        
        };
        
          function set(key,value){
                return localStorage.setItem(key,value);
            };
          function get(key){
                return localStorage.getItem(key);
            };
          function destroy(key){
                return localStorage.removeItem(key);
            };
    }
})();