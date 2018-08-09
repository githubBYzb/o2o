

$(function() {
	setDialog();

	// 进入页面，焦点在用户名文本框上
	$("#userName").focus();
});


function doLogin() {
	var validateResult = true;
	// easyui 表单验证
	$('#loginTable input').each(function() {
		if ($(this).attr('required') || $(this).attr('validType')) {
			if (!$(this).validatebox('isValid')) {
				// 如果验证不通过，则返回false
				validateResult = false;
				return;
			}
		}
	});
	if (validateResult == false) {
		// 如果验证不通过，则不执行登录操作
		return;
	}

	$("#login_msg").html("登录中，请稍后...");

    var userName = $("#userName").val();
    var password = $("#password").val();
    $.ajax({
		async : false,
		url : "/o2o/frontend/logincheck",   // 请求的action路径
		type:'POST',
		data : {"userName":userName,"password":password},
		dataType : "json",

		error : function() {// 请求失败处理函数
            /*alert(userName);
            alert(password);*/
            var textstatus = $("#textStatus");
            var errormsg = $("#errorThrown");
            /*alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(XMLHttpRequest.responseText);
            alert(JSON.stringify(textstatus));
            alert(JSON.stringify(errormsg))*/;
			alert('请求失败');
		},

        success: function (data) {// 请求成功后处理函数。
			//alert(data.success);
			//alert(data.userType);
            if (data.success) {
                $("#login_msg").html("登录成功");

                document.cookie = "yourusername=" + data.yourusername;
                document.cookie = "yourpwd=" + data.yourpwd;
                document.cookie = "yourname=" + data.yourname;
                document.cookie = "yourgender=" + data.yourgender;
                document.cookie = "youremail=" + data.youremail;

                /*sessionStorage.setItem("yourusername",data.yourusername);
                sessionStorage.setItem("yourpwd",data.yourpwd);
                sessionStorage.setItem("yourname",data.yourname);
                sessionStorage.setItem("yourgender",data.yourgender);
                sessionStorage.setItem("youremail",data.youremail);*/


                if (data.userType == 1) {
                    window.location.href = "/o2o/frontend/index";
                }
                else if (data.userType == 2) {
					window.location.href = "/o2o/shopadmin/shoplist";
                }
                else if (data.userType == 3) {

                }
            } else {// 后台异常处理
                $("#login_msg").html(data.errMsg);
            }
        }
    });
}



/**
 * 执行reset操作
 */
function doReset() {
	$("#userName").val("");
	$("#password").val("");
	$("#login_msg").html("&nbsp;");
}

/** --------------操作弹出框------------------* */
// 设置弹出框的属性
function setDialog() {
	$('#login').dialog({
		title : '用户登录',
		modal : true, // 模式窗口：窗口背景不可操作
		collapsible : true, // 可折叠，点击窗口右上角折叠图标将内容折叠起来
		resizable : true, // 可拖动边框大小
		closable : false
	// 不提供关闭窗口按钮
	});
}