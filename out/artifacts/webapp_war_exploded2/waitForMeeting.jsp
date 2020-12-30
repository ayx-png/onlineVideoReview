<%--
  Created by IntelliJ IDEA.
  User: machengcong
  Date: 2020/12/21
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>会议等待</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/waitForMeeting.css">
</head>
<body>
    <div class="header">
        <div class="col-lg-3 ">
            <img src="images/logo2.a2ebc408.png" alt="logo" class="header-logo">
        </div>
        <div class="col-lg-6 header-title">科云评审系统</div>
        <div class="userKey" >
            欢迎您，${user.username}
        </div>
    </div>
    <div class="body">
        <div class="enterForm">
            <div class="meetingMsg">
                <div class="meetingTopic">会议主题：啦啦啦啦</div>
                <div>
                    <span>等待剩余时间：</span>
                    <sapn class="time">50</sapn>
                </div>
            </div>
            <button type="button" class="enterBtn">进入会议</button>
        </div>
    </div>
</body>
</html>