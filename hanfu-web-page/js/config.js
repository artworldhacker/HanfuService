function post(url, data, successfn, errorfn) {
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        success: function (d) {
            if (d.success) {
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


function post(url, data, successfn) {
    data = (data == null || data == "" || typeof(data) == "undefined") ? {"date": new Date().getTime()} : data;
    $.ajax({
        type: "post",
        data: data,
        url: url,
        dataType: "json",
        success: function (d) {
            successfn(d);
        },
        error: function (e) {
            alert(e);
        }
    });
};