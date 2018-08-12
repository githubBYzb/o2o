/*var oldcount = sessionStorage.getItem("yourusername");
var oldpwd = sessionStorage.getItem("yourpwd");
var oldname = sessionStorage.getItem("yourname");
var oldgender = sessionStorage.getItem("yourgender");
var oldemail = sessionStorage.getItem("youremail");*/

var oldcount = document.cookie.split(";")[0].split("=")[1];
var oldpwd = document.cookie.split(";")[1].split("=")[1];
var oldname = document.cookie.split(";")[2].split("=")[1];
var oldgender = document.cookie.split(";")[3].split("=")[1];
var oldemail = document.cookie.split(";")[4].split("=")[1];

$(".oldcount").html(oldcount);
$(".oldpwd").html(oldpwd);
$(".oldname").html(oldname);
$(".oldgender").html(oldgender);
$(".oldemail").html(oldemail);


function changecount() {
    var newcount = $("#newcount").val();
    $.ajax({
            async: false,
            url: "/o2o/frontend/changecount",
            type: "post",
            dataType: "json",
            data: {"oldcount": oldcount, "newcount": newcount},

            success: function (data) {
                if (data.success){
                    alert("修改成功");
                    //sessionStorage.setItem("yourusername",newcount);
                    document.cookie = "yourusername=" + newcount;
                    //刷新页面会导致cookie数组读取有误
                    //window.location.reload(false);
                    alert("请重新登录！")
                    window.location.href = "/o2o/frontend/login";
                }
            },

            error: function () {
                alert(JSON.stringify(XMLHttpRequest.status));
                alert(JSON.stringify(XMLHttpRequest.readyState));
                alert(JSON.stringify(textStatus));
                alert("修改失败");
                window.location.reload(false);
            },
        }
    )
}


function changepwd() {
    var newpwd1 = $("#newpwd1").val();
    var newpwd2 = $("#newpwd2").val();
    if (newpwd1 == newpwd2){
        $.ajax({
                async: false,
                url: "/o2o/frontend/changepwd",
                type: "post",
                dataType: "json",
                data: {"oldpwd": oldpwd, "newpwd": newpwd2,"count":oldcount},

                success: function (data) {
                    if (data.success){
                        alert("修改成功");
                        document.cookie = "yourpwd=" + newpwd2;
                        //刷新页面会导致cookie数组读取有误
                        //window.location.reload(false);
                        alert("请重新登录！")
                        window.location.href = "/o2o/frontend/login";
                    }
                },

                error: function () {
                    alert(JSON.stringify(XMLHttpRequest.status));
                    alert(JSON.stringify(XMLHttpRequest.readyState));
                    alert(JSON.stringify(textStatus));
                    alert("修改失败");
                    window.location.reload(false);
                },
            }
        )
    }else{
        alert("两次输入的密码不一致，请重新输入！");
        window.location.reload(false);
    }

}



function changename() {
    var newname = $("#newname").val();
    $.ajax({
            async: false,
            url: "/o2o/frontend/changename",
            type: "post",
            dataType: "json",
            data: {"oldname": oldname, "newname": newname},

            success: function (data) {
                if (data.success) {
                    alert("修改成功");
                    document.cookie = "yourname=" + newname;
                    //刷新页面会导致cookie数组读取有误
                    //window.location.reload(false);
                    alert("请重新登录！")
                    window.location.href = "/o2o/frontend/login";
                }
            },

            error: function () {
                alert(JSON.stringify(XMLHttpRequest.status));
                alert(JSON.stringify(XMLHttpRequest.readyState));
                alert(JSON.stringify(textStatus));
                alert("修改失败");
                window.location.reload(false);
            },
        }
    )
}

function changegender() {
    var newgender = $("#newgender").val();
    var name = oldname;
    $.ajax({
            async: false,
            url: "/o2o/frontend/changegender",
            type: "post",
            dataType: "json",
            data: {"oldgender": oldgender, "newgender": newgender,"name":name},

            success: function (data) {
                if (data.success) {
                    alert("修改成功");
                    document.cookie = "yourgender=" + newgender;
                    //刷新页面会导致cookie数组读取有误
                    //window.location.reload(false);
                    alert("请重新登录！")
                    window.location.href = "/o2o/frontend/login";
                }
            },

            error: function () {
                alert(JSON.stringify(XMLHttpRequest.status));
                alert(JSON.stringify(XMLHttpRequest.readyState));
                alert(JSON.stringify(textStatus));
                alert("修改失败");
                window.location.reload(false);
            },
        }
    )
}

function changeemail() {
    var newemail = $("#newemail").val();
    var name = oldname;
    $.ajax({
            async: false,
            url: "/o2o/frontend/changeemail",
            type: "post",
            dataType: "json",
            data: {"oldemail": oldemail, "newemail": newemail,"name":name},

            success: function (data) {
                if (data.success) {
                    alert("修改成功");
                    document.cookie = "youremail=" + newemail;
                    //刷新页面会导致cookie数组读取有误
                    //window.location.reload(false);
                    alert("请重新登录！")
                    window.location.href = "/o2o/frontend/login";
                }
            },

            error: function () {
                alert(JSON.stringify(XMLHttpRequest.status));
                alert(JSON.stringify(XMLHttpRequest.readyState));
                alert(JSON.stringify(textStatus));
                alert("修改失败");
                window.location.reload(false);
            },
        }
    )
}
