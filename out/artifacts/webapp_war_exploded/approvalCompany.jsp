<%--
  Created by IntelliJ IDEA.
  User: machengcong
  Date: 2020/12/21
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>企业审批</title>
    <link rel="stylesheet" href="./css/approvalCompany.css">
    <script type="application/javascript" src="./js/approvalCompany.js"></script>
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
        <div class="approvalContainer">
            <div class="btn">
                <span>请从下列企业中选择合格的企业参加评审：</span>
                <button type="button" id="submitBtn">提交</button>
            </div>
            <div class="companies">
                <form action="approvalCompany" id="approvalForm" method="post">
                    <div class="companiesContainer"></div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>