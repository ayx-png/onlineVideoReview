//检查用户名
function checkUsername() {
    //1.获取用户名值
    var username = $("#username").val();
    //2.定义正则
    var reg_username = /^\w{3,20}$/;

    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if (flag) {
        //用户名合法
        $("#username").css("border", "");
    } else {
        //用户名非法,加一个红色边框
        $("#username").css("border", "1px solid red");
    }

    return flag;
}
//检查密码
function checkPassword() {
    //1.获取密码值
    var password = $("#password").val();
    //2.定义正则
    var reg_password = /^\w{3,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if (flag) {
        //密码合法
        $("#password").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#password").css("border", "1px solid red");
    }
    return flag;
}

//验证密码
function checkPasswordok() {
    var passwordok = $("#passwordok").val();
    var flag = (passwordok == password.value);
    if (flag) {
        $("#passwordok").css("border", "");
    } else {
        $("#passwordok").css("border", "1px solid red");

    }
    return flag;
}
//检查密码
function checkPhoneNumber() {
    //1.获取密码值
    var phonenumber = $("#phonenumber").val();
    //2.定义正则
    var reg_phonenumber = /^1[3-9][0-9]{9}$/;

    //3.判断，给出提示信息
    var flag = reg_phonenumber.test(phonenumber);
    if (flag) {
        //密码合法
        $("#phonenumber").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#phonenumber").css("border", "1px solid red");
    }
    return flag;
}
//检查密码
function checkMail() {
    //1.获取密码值
    var mail = $("#mail").val();
    //2.定义正则
    var reg_mail = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;

    //3.判断，给出提示信息
    var flag = reg_mail.test(mail);
    if (flag) {
        //密码合法
        $("#mail").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#mail").css("border", "1px solid red");
    }
    return flag;
}
window.onload = function () {
    var sendbtn = document.getElementById("sendbtn");
    //alert(sendbtn);
    sendbtn.onclick = function () {
        // alert(1);
        if (!checkUsername()) {
            alert("姓名要3到20个字符");
            return false;
        }
        if (!checkPassword()) {
            alert("密码3到20位");
            return false;
        }
        if (!checkPasswordok()) {
            alert("再次输入的密码与密码不匹配");
            return false;
        }
        if (!checkPhoneNumber()) {
            alert("电话号码格式不对");
            return false;
        }
        if (!checkMail()) {
            alert("邮箱地址格式不对");
            return false;
        }
    };
}