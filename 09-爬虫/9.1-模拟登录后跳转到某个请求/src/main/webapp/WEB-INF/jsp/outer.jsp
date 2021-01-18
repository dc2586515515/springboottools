<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>安徽省粮食和物资储备局</title>
    <!-- 页面基本设置禁止随意更改 -->
    <!-- 通用样式 -->
    <link rel="stylesheet" href="http://36.7.135.171:10081/outer-web/css/reset.css">
    <!-- end -->
    <link rel="stylesheet" href="http://36.7.135.171:10081/outer-web/css/style.css">
    <link rel="shortcut icon" href="/outer-web/images/favicon.ico" type="image/x-icon"/>
</head>
<body style="display: none">
<div class="wrapper" style="background-color: white;">
</div>
<img class="login_bg" src="http://36.7.135.171:10081/outer-web/images/login.png">
<div class="login_wrap">
    <div class="login_cont">
        <img src="http://36.7.135.171:10081/outer-web/images/login_logo2.png" alt="">
        <form style="margin-left: 35px;" class="login_form" action="http://36.7.135.171:10081/outer-web/j_spring_security_check.do" method="post" id="login_form">
            <input class="user" type="text" id="username" name="j_username" placeholder="用户名" onKeyPress="IsEnterKeyPress()" value="*****">
            <input class="pass" type="password" id="password" name="j_password" placeholder="*******" onKeyPress="IsEnterKeyPress()" value="*****">
            <a id="submitButton" class="login_but" style="width: 280px;" href="javascript:;">登&nbsp;&nbsp;录</a>
        </form>
    </div>
    <div class="copyright">Copyright©1999-2016 安徽省粮食和物资储备局 版权所有</div>
</div>

<script src="http://36.7.135.171:10081/outer-web/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript">
    function IsEnterKeyPress() {
        var lKeyCode = (navigator.appname == "Netscape") ? event.which : window.event.keyCode;
        if (lKeyCode == 13) {
            var name = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            if (name == 'djbhcsyh') {
                $("#login_form").submit();
                return true;
            }

            if (name != null && name != '') {
                var first = name.toString().substring(0, 1);
                if (first != 'p' && first != 'j' && first != 'k' && first != 'q' && first != 'K' && first != 'Q' && first != 'C' && first != 'c' && first != 's') {
                    alert("该系统只能企业和库点用户登陆！")
                    return false;
                } else {
                    if (first == 's' && name != "superadmin") {
                        alert("该系统只能企业和库点用户登陆！")
                        return false;
                    }
                }
            }
            if (isEmpty(name) || isEmpty(password)) {
                alert("用户名/密码为空,请重新输入!");
                return false;
            }
            $("#login_form").submit();
        }
    }

    var error = 'null';
    if (error != 'null' && error.length > 0) {
        alert('');
    }
    $(".login_but").click(function () {
        var name = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if (name == 'djbhcsyh') {
            $("#login_form").submit();
            return true;
        }
        if (name != null && name != '') {
            var first = name.toString().substring(0, 1);
            if (first != 'p' && first != 'j' && first != 'k' && first != 'q' && first != 'K' && first != 'Q' && first != 'C' && first != 'c' && first != 's') {
                alert("该系统只能企业和库点用户登陆！");
                return false;
            } else {
                if (first == 's' && name != "superadmin") {
                    alert("该系统只能企业和库点用户登陆！")
                    return false;
                }
            }
        }
        if (isEmpty(name) || isEmpty(password)) {
            alert("用户名/密码为空,请重新输入!");
            return false;
        }
        $("#login_form").submit();
    });

    function isEmpty(str) {
        if (str && str != null && str.length > 0) {
            return false;
        }
        return true;
    }
</script>

<script type="application/javascript">
    window.onload = function () {
        document.getElementById("submitButton").click();
    }
</script>
</body>
<div FirebugVersion="1.3.3" style="display: none;" id="_firebugConsole"></div>
</html>