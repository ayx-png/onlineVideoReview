<%--
  Created by IntelliJ IDEA.
  User: machengcong
  Date: 2020/12/30
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>加入会议</title>
    <link rel="stylesheet" href="./css/meetingJoin.css">
    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/joinMeeting.js"></script>
</head>
<body>
    <div class="header">
        <div>
            <img src="images/logo2.a2ebc408.png" alt="logo" class="header-logo">
        </div>
    </div>
    <div class="joinBody">
        <form action="joinMeeting" id="joinMeetingForm" method="post">
            <p class="joinTitle">加入会议</p>
            会议号：<input type="text" name="meetingID" id="meetingID">
            <button id="fastJoinMeeting">加入会议</button>
            <tr>
                <td>
                    <br><br>
                    &nbsp&nbsp
                    <input type="checkbox" name="audio" id="audio">入会时静音
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                    <input type="checkbox" name="video" id="video">入会时关闭视频
                </td>
            </tr>
        </form>
        <div class="errMsg">${messageModel.msg}</div>
        <div class="networkTest">
            <span>进入会议前请点击网络测试按钮，测试网络和设备状况！</span>
            <a href="pre-test.html"><button id="pre-test">网络测试</button></a>
        </div>
    </div>
</body>
</html>
