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
    <link rel="stylesheet" href="css/register.css">
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
        <form id="registerForm" action="https://localhost:8443/onlineMeeting/register" method="post">
            用户名：
            <input type="text" name="username" id="username" placeholder="3到10位字符">
            <br>
            密&nbsp&nbsp&nbsp码：
            <input type="text" name="password" id="password" placeholder="3到10位密码">
            <br>
            密码确认：
            <input type="text" name="passwordConf" id="passwordConf" placeholder="再次输入密码">
            <br>
            企业名：
            <input type="text" name="companyName" id="companyName" placeholder="输入企业名">
            <br>
            手机号：
            <input type="text" name="phoneNumber" id="phoneNumber" placeholder="输入手机号">
            邮&nbsp&nbsp&nbsp箱：
            <input type="text" name="mail" id="mail" placeholder="请输入邮箱地址">
            <br>
            <button type="button" id="registerBtn">注册</button>
            注册完毕：<a href="./login.jsp">立即登录</a>
            <br>
            <br>
            <div class="errorMsg">
                <span id="err">${messageModel.msg}</span> <br>
            </div>
        </form>
    </div>
    </div>
</body>
</html>
