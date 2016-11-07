function post(url, data, successfn, errorfn) {
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        success: function (d) {
            if (d.status) {
                if(!d.status && '10001' == d.statusCode){//用户未登陆
                    window.location.href = "login.html";
                }
                successfn(d);
            } else {
                errorfn(d);
            }
        },
        error: function (e) {
            alert(e);
        }
    });
};


function postAjax(url, data, successfn) {
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        success: function (d) {
            if(!d.status && '10001' == d.statusCode){//用户未登陆
                window.location.href = "login.html";
            }
            successfn(d);
        },
        error: function (e) {
            alert(e);
        }
    });
};

function postAsync(url, data, successfn){
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        async: false,
        dataType: "json",
        success: function (d) {
            if(!d.status && '10001' == d.statusCode){//用户未登陆
                window.location.href = "login.html";
            }
            successfn(d);
        },
        error: function (e) {
            alert(e);
        }
    });
}

function getAjax(url, data, successfn) {
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "get",
        data: data,
        url: url,
        dataType: "json",
        success: function (d) {
            if(!d.status && '10001' == d.statusCode){//用户未登陆
                window.location.href = "login.html";
            }
            successfn(d);
        },
        error: function (e) {
            alert(e);
        }
    });
};