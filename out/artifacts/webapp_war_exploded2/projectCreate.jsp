<%--
  Created by IntelliJ IDEA.
  User: machengcong
  Date: 2020/12/15
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/userSpace.css" />
    <link rel="stylesheet" href="./css/projectSign.css" />
    <title>用户空间</title>
</head>
<body>
    <div class="header">
        <img class="header-logo" src="./images/logo2.a2ebc408.png" alt="header-logo" />
        <span class="title">用户空间</span>
        <div class="userKey">

            <i class="fa">&#xf2bd;</i>
            <span>欢迎你，${user.username}</span>
        </div>
    </div>
    <div class="body clearfix">
        <div class="userInform">
            <span class="headone">用户信息</span>

            <button class="button" type="button">信息修改</button>

            <form class="userForm clearfix">
                <label><span>用户名:</span><input type="text" name="userName" /></label>


                <label><span>性&nbsp别:</span><input type="text" name="gender" /></label>

                <label><span>密&nbsp码:</span><input type="text" name="passWord" /></label>

                <label><span>手机号:</span><input type="text" name="phoneNumber" /></label>
                <label><span>邮&nbsp箱:</span><input type="text" name="email" /></label>

                <label><span>企业名:</span><input type="text" name="companyName" /></label>


            </form>
        </div>
        <div class="projectInform">
            <span class="headone">已报名项目</span>

            <button class="button" type="button">项目报名</button>

            <form class="projectForm clearfix">
                <br />
                <br />
                <br />
                <input type="text" />
                <input type="text" />
                <input type="text" />
            </form>
        </div>
        <div class="histroyInform">
            <span class="headone">历史会议</span>
            <form class="histroyForm">
            </form>
        </div>
    </div>
    <div class="shade">
        <div class="signForm clearfix">
            <form action="createProject" id="createProjectForm" method="post">
                <div>
                    <span>创建项目</span>
                    <a href="userSpace.jsp">
                        <i class="fa fa-close"></i>
                    </a>
                </div>
                <div class="input">
                    <label for="projectName">项目名称:</label>
                    <input type="text" name="projectName" id="projectName" />
                </div>
                <div>
                    <button type="submit" class="submitBtn">确认创建</button>
                </div>
            </form>
            <div class="errMsg">${messageModel.msg}</div>
        </div>
    </div>
</body>
</html>