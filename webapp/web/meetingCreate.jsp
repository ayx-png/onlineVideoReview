<%--
  Created by IntelliJ IDEA.
  User: 我拥有它
  Date: 2020/11/20
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>创建会议</title>
    <link rel="stylesheet" href="./css/meetingCreate.css">
    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/meetingCreate.js"></script>
</head>
<body>
    <!----------------  网站标题  ------------------>
    <div class="header">
        <div>
            <img src="images/logo2.a2ebc408.png" alt="logo" class="header-logo">
        </div>
    </div>
    <!----------------  创建会议  ------------------>
    <div class="meetingBody">
        <form id="meetingForm" action="http://localhost:8080/onlineMeeting/meetingCreate" method="post">
            <!------------------------ 创建会议 -------------------->
            <div class="createBody">
                <p class="createBody_P">创建会议</p>
                会议主题:
                <input type="text" placeholder="评审组xx的会议" name="topic" id="topic">
                会议人数:
                <input type="text" placeholder="不超过17人" name="memberNum" id="memberNum">
                <div class="meetingTime">
                    会议时长:
                    <select class="hour" id="hour" name="hour">
                        <option value="0">0小时</option>
                        <option value="1">1小时</option>
                        <option value="2">2小时</option>
                        <option value="3">3小时</option>
                        <option value="4">4小时</option>
                        <option value="5">5小时</option>
                        <option value="6">6小时</option>
                        <option value="7">7小时</option>
                        <option value="8">8小时</option>
                    </select>
                    <select class="minute" id="minute" name="minute">
                        <option value="0">0分</option>
                        <option value="10">10分</option>
                        <option value="20">20分</option>
                        <option value="30">30分</option>
                        <option value="40">40分</option>
                        <option value="50">50分</option>
                    </select>
                </div>
                <input type="checkbox" id="audio" name="audio">入会时打开麦克风
                <input type="checkbox" id="video" name="video">入会时打开摄像头
                <div id="createButton">
                        <button type="button" id="submitBtn">创建会议</button>
                </div>
                <div>
                    <p id="err">${messageModel.msg}</p>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
