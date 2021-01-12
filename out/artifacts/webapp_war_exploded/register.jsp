<%--
  Created by IntelliJ IDEA
  User: admin
  Date: 2020/11/9
  Time: 11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <link rel="stylesheet" href="./css/register.css">
    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/register.js"></script>
</head>
<body>
    <!----------------  网站标题  ------------------>
    <div class="header">
    <img src="images/logo2.a2ebc408.png" alt="logo" class="header-logo">
    <div class="header-title">科云评审系统用户注册</div>
    </div>

    <!---------------- 注册表单  ------------------>
    <div class="body">
    <div class="registerbox">
        <div class="formBackground">
            <form id="registerForm" action="register" method="post">
                <div class="info">
                    <span>用户名：</span>
                    <input type="text" name="username" id="username" placeholder="3到10位字符">
                </div>
                <div class="info">
                    <span>密码：</span>
                    <input type="password" name="password" id="password" placeholder="3到10位密码">
                </div>
                <div class="info">
                    <span>密码确认：</span>
                    <input type="password" name="passwordConf" id="passwordConf" placeholder="再次输入密码">
                </div>
                <div class="info">
                    <span>企业名：</span>
                    <input type="text" name="companyName" id="companyName" placeholder="输入企业名">
                </div>
                <div class="info">
                    <span>手机号：</span>
                    <input type="text" name="phoneNumber" id="phoneNumber" placeholder="输入手机号">
                </div>
                <div class="info">
                    <sapn>邮箱：</sapn>
                    <input type="text" name="mail" id="mail" placeholder="请输入邮箱地址">
                </div>
                <div class="btn">
                    <button type="button" id="registerBtn">注册</button>
                    注册完毕：<a href="./login.jsp">立即登录</a>
                </div>
                <div class="errorMsg">
                    <span id="err">${registerMessageModel.msg}</span>
                </div>
            </form>
        </div>
    </div>
    </div>
</body>
</html>
