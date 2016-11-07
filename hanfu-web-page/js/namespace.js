(function($){
    var $base = '/hanfu/';
    $.URL = {
        "user": {
            "login": $base + "user/login"
        },
        "index":{
            "menu" : $base+"index/menu",
            "logout" : $base+"index/logout",
            "getSessionUser" : $base+"index/getSessionUser"
        }

    }
})(jQuery);


