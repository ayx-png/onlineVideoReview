<%--
  Created by IntelliJ IDEA
  User: admin
  Date: 2020/11/6
  Time: 15:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户登录</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/login.js"></script>
</head>
<body>
    <!----------------  网站标题  ------------------>
    <div class="header">
        <div class="col-lg-3 ">
            <img src="images/logo2.a2ebc408.png" alt="logo" class="header-logo">
        </div>
        <div class="col-lg-6 header-title">科云评审系统</div>
    </div>

    <!----------------  登录表单  ------------------>
    <div class="body">
        <img src="./images/IMG_20200915_155804.jpg" alt="background-image" class="background-image blur">

        <!------------------- 登录表-------------->
        <div class="body-form">
            <!--登录文字标题-->
            <div class="lo_form_left">
                <p>用户登录</p>
                <p>USER LOGIN</p>
            </div>
            <!--用户名密码输入框-->
            <div class="lo_form_center">
                <!---------------- 注册信息 -------------------->
                <div class="registerMsg">
                    ${message}
                </div>
                <form id="loginForm" action="login" method="post">
                    <!--<input type="hidden" name="action" value="login">-->
                    <table style="margin-top: 25px;">
                        <tr>
                            <td class="td_left">
                                <label for="username">用户名</label>
                            </td>
                            <td class="td_right">
                                <input type="text" id="username" name="username" placeholder="请输入账号" value="${SignInMessageModel.object.username}">
                            </td>
                        </tr>
                        <tr>
                            <td class="td_left">
                                <label for="password">密码</label>
                            </td>
                            <td class="td_right">
                                <input type="password" id="password" name="password" placeholder="请输入密码" value="${signInMessageModel.object.password}">
                            </td>
                        </tr>
                        <tr>
                            <td class="td_submit">
                                <span id="errorMsg">${signInMessageModel.msg}</span>
                                <button type="button" class="submit" id="enter">登&nbsp&nbsp&nbsp&nbsp录</button>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_mm">
                                <br/>
                                <a href="#">忘记密码</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <!--注册账号-->
            <div class="lo_form_right">
                <p>
                    没有账号？
                    <a href="register.jsp">立即注册</a>
                </p>
            </div>
        </div>
        <div class="footer">
            <div class="footer-line">
                <h4>下载中心</h4>
                <ul>
                    <li><a href="#">客户端</a></li>
                    <li><a href="#">移动端</a></li>
                </ul>
            </div>
            <div class="footer-line">
                <h4>关于</h4>
                <ul>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">友情链接</a></li>
                </ul>
            </div>
            <div class="footer-line">
                <h4>联系方式</h4>
                <ul>
                    <li><a href="#">新浪微博</a></li>
                    <li><a href="#">电子邮件</a></li>
                    <li><a href="#">联系电话</a></li>
                    <li><a href="#">公司地址</a></li>
                </ul>
            </div>
        </div>

    </div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<!--    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>-->
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>-->
</body>
</html>