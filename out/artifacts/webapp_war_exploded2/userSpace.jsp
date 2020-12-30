<%--
  Created by IntelliJ IDEA.
  User: machengcong
  Date: 2020/12/15
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,inital-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/userSpace.css" />
    <link rel="stylesheet" href="./css/common.css">
    <script src="./js/jquery-3.5.1.min.js"></script>
    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/userSpace.js"></script>
    <title>用户空间</title>
</head >
<body>
    <div class="header">
    <img class="header-logo" src="./images/logo2.a2ebc408.png" alt="header-logo" />
    <span class="title">用户空间</span>
    <div class="userKey" >
        欢迎您，${user.username}
    </div>
    </div>
    <div  class="body clearfix">

        <div class="userInform">
            <span class="headone">用户信息</span>
            <a href="infoChange.jsp">
                <button class="button" type="button">信息修改</button>
            </a>
            <ul class="userForm">
                <li><span>用户名:</span>&nbsp&nbsp&nbsp&nbsp${user.username}</li>
                <li><span>手机号:</span>&nbsp&nbsp&nbsp&nbsp${user.phoneNumber}</li>
                <li><span>邮&nbsp箱:</span>&nbsp&nbsp&nbsp&nbsp${user.mail}</li>
                <li><span>企业名:</span>&nbsp&nbsp&nbsp&nbsp${user.companyName}</li>
            </ul>
        </div>
        <div class="projectInform">
            <span class="headone">已报名项目</span>
            <a href="projectSign.jsp">
                <button class="button" type="button">项目报名</button>
            </a>
            <a href="projectCreate.jsp">
                <button class="button" type="button">创建项目</button>
            </a>
            <ul class="projectForm">
            </ul>
        </div>
        <div class="histroyInform">
            <span class="headone">历史会议</span>
            <a href="pre-test.html">测试</a>
            <form class="histroyForm">
            </form>
        </div>
    </div>
<%--    <c:if test="${Modelmessage.code==1}">--%>
<%--        <div class="notification">密码修改成功</div>--%>
<%--    </c:if>--%>
</body>
</html>

