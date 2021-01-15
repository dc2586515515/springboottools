<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title></title>
    <!-- Le styles -->
    <link rel='stylesheet' type='text/css' href='http://172.10.8.54:90/javascript/plugin/bootstrap/dist/css/bootstrap.css'>
    <link rel='stylesheet' type='text/css' href='http://172.10.8.54:90/javascript/font-icon/style.css'>
    <link rel='stylesheet' type='text/css' href='http://172.10.8.54:90/javascript/skin/dist/css/skin_blue.css'>
    <link rel='stylesheet' type='text/css' href='http://172.10.8.54:90/views/home/css/login.css'>
    <!-- Javascript -->
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/jquery/dist/jquery.js"></script>
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/js-md5/src/md5.js"></script>
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/jsencrypt/bin/jsencrypt.min.js"></script>
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/hikly/dist/js/HikLY.js"></script>
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/libs/libs.js"></script>
    <!-- <script type="text/javascript" src="http://172.10.8.54:90/javascript/qrcode/jquery.qrcode.js"></script> -->
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/qrcodejs/qrcode.js"></script>
    <script type="text/javascript" src="http://172.10.8.54:90/javascript/plugin/js-base64/base64.js"></script>
    <!-- 涉及到页面跳转，在DOM元素加载前执行 -->
    <script type="text/javascript">
        window.basePath = "";
        window.IPLockMsg = ''; // IP锁定提示信息
        //如果顶层不是自己，就跳转顶层到自己 xx 2015/6/11
        if (window.top !== window.self) {
            try {
                window.top.location.href = window.location.href;
            } catch (ex) {
                console.log(ex);
            }
        }
        // 验证是否需要导入license
        $._ajax({
            url: basePath + "/home/licenseCheck.action?time=" + new Date().getTime(),
            method: "post",
            success: function (data) {
                if (!(data.success) && data.data == 1) {
                    window.document.location = basePath + "/home/firstUseSet.action";
                } else if (!(data.success)) {
                    window.document.location = basePath + "/home/licenseUpload.action?errorMsg=" + encodeURIComponent(data.message);
                }
            }
        });

        // 共享组件不定制request里面设置error_code属性，
        // 这里把errorMessage当做errorCode处理：20024代表会话已经登录
        if ("20024" == '') {
            window.document.location = basePath + "/" + "home/index.action";
        }

        // error_code为20020 代表license已失效
        if ("20020" == '') {
            alert("License已失效,请重新导入");
            window.document.location = basePath + "/" + "home/licenseUpload.action";
        }
    </script>
</head>
<body style="display: none">
<!-- 展示项目编号信息（安全需求） -->
<div sys_jkn="JKN20180531_220," style="display:none"></div>
<!-- 页面超时标志，ajax超时后可能会直接返回登录页 -->
<div type="hidden" data-flag="this_is_login_input_title"></div>
<!-- 登录页容器 -->
<div class="login-container" id="loginContainer">
    <!-- 登录页背景图 -->
    <img class="login-bg-img" id="loginBgImg" src="">

    <!-- 登录页头部 -->
    <div class="login-header skin-bgcolor">
        <div class="login-header-wrap">
            <div class="login-sitename">
                <img src=""/>
                <span id="siteName"></span>
            </div>
        </div>
    </div>

    <!-- 软件二维码信息 -->
    <div class="qrcode-show-icon" id="showQrcode"><i class="qrcode-icon"></i>软件二维码信息</div>
    <div class="qrcode-show-dialog hide" id="qrcodeShowDialog">
        <div class="qrcode-show-mask"></div>
        <div class="qrcode-img" id="qrcodeImg"></div>
    </div>

    <!-- 登录页内容区域 -->
    <div class="login-wrap">
        <div class="login-content">
            <!-- 登录框 -->
            <%--            <form class="login-frame" id="loginForm" action="http://172.10.8.54:90/home/login?service=http%3A%2F%2F172.10.8.54%3A90%2Fuser%2Findex.action" method="post">--%>
            <form class="login-frame" id="loginForm" action="http://172.10.8.54:90/home/login?service=http%3A%2F%2F172.10.8.54%3A90%2Fcoms%2F" method="post">
                <div class="login-frame-title skin-color">欢迎登录</div>

                <!-- 错误信息 -->
                <div class="login-error-msg">
                    <i class="icon-an-delete-circle" style='display:none'></i>
                    <span></span>
                </div>

                <!-- 用户名 -->
                <div class="login-username">
                    <input class="form-control hikly-input" id="username" type="text" name="username" placeholder="用户名" maxlength="30" value="xxx" autocomplete="off">
                </div>

                <!-- 密码 -->
                <div class="login-password">
                    <input id="password" type="hidden" name="password" autocomplete="off">
                    <input class="form-control hikly-input" id="password_x" type="password" autocomplete="off" placeholder="密码" value="xxx" maxlength="32">
                </div>

                <!-- 验证码 -->
                <div class="login-codes">

                </div>

                <!-- 隐藏域 -->
                <input type="hidden" id="pwdLevel" name="pwdLevel"/>
                <input type="hidden" id="loginWay" name="loginWay"/>
                <input type="hidden" id="clientIP" name="clientIP" value="172.10.1.251"/>
                <input type="hidden" id="serviceIP" name="serviceIP" value="172.10.1.251"/>
                <input type="hidden" id="codeId" name="codeId" value="d4084d83-1cf7-4eda-be23-915f295dab06"/>
                <input type="hidden" id="vCode" name="vCode" value="6431e5e0-62dd-4a3f-b84d-eb5889d0dd39"/>
                <input type="hidden" id="clientMAC" name="clientMAC" value=" "/>
                <input type="hidden" id="loginType" name="loginType" value="3"/>
                <input type="hidden" name="_eventId" value="submit"/>
                <input type="hidden" id="errorCode" name="errorCode" value=""/>

                <!-- 登录按钮 -->
                <button class="btn hikly-btn-primary submit-btn" id="submitBtn" type="submit" onclick="return false;">登 录</button>

                <!-- https证书下载提示 -->
                <div class="https-certificate" id="downloadCertificate" style="display: none;">
                    <span>下载安装<a href="/views/home/file/installPackage.rar">根证书</a>可使平台使用更加顺畅</span>
                </div>
            </form>
            <div class="login-frame-shadow"></div>
        </div>
    </div>

    <!-- 登录页底部 -->
    <div class="login-footer">
        <div class="login-copyright">
            <span id="copyright">杭州海康威视系统技术有限公司 版权所有</span>
        </div>
        <div class="login-trial-date">
            <span id="licenseInfo"></span>
        </div>
    </div>
</div>
<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="http://172.10.8.54:90/views/home/js/login.js"></script>
</body>
<script type="application/javascript">
    window.onload = function () {
        document.getElementById("submitBtn").click();
    }
</script>
</html>
