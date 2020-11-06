//检查用户名
function checkUsername() {
    //1.获取用户名值
    let username = $("#username").val();
    //2.定义正则
    let reg_username = /^\w{3,20}$/;

    //3.判断，给出提示信息
    let flag = reg_username.test(username);
    if (flag) {
        //用户名合法
        $("#username").css("border", "");
    } else {
        //用户名非法,加一个红色边框
        $("#username").css("border", "1px solid red");
        $("#err").html("姓名要3到20个字符");
    }
    return flag;
}
//检查密码
function checkPassword() {
    //1.获取密码值
    let password = $("#password").val();
    //2.定义正则
    let reg_password = /^\w{3,20}$/;

    //3.判断，给出提示信息
    let flag = reg_password.test(password);
    if (flag) {
        //密码合法
        $("#password").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#password").css("border", "1px solid red");
        $("#err").html("密码要3到20位");
    }
    return flag;
}

//检查确认密码
function checkPasswordConf() {
    //判断确认密码和密码是否一致
    if( $("#passwordConf").val() != $("#password").val() ){
        $("#err").html("密码不一致");
        return;
    }
    return true;
}
//验证手机号
function checkPhoneNumber() {
    //1.获取手机号
    let phoneNumber = $("#phoneNumber").val();
    //2.定义正则
    let reg_phoneNumber = /^1[3-9][0-9]{9}$/;

    //3.判断，给出提示信息
    let flag = reg_phoneNumber.test(phoneNumber);
    if (flag) {
        //密码合法
        $("#phoneNumber").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#phoneNumber").css("border", "1px solid red");
        $("#err").html("请输入有效手机号");
    }
    return flag;
}
//检查邮箱
function checkMail() {
    //1.获取邮箱
    let mail = $("#mail").val();
    //2.定义正则
    let reg_mail = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;

    //3.判断，给出提示信息
    let flag = reg_mail.test(mail);
    if (flag) {
        //密码合法
        $("#mail").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#mail").css("border", "1px solid red");
        $("#err").html("邮件格式不正确");
    }
    return flag;
}
$(document).ready(function (){
    $("#registerBtn").click(function () {
        if (!checkUsername()) {
            return;
        }
        if (!checkPassword()) {
            return;
        }
        if (!checkPasswordok()) {
            return;
        }
        if (!checkPhoneNumber()) {
            return;
        }
        if (!checkMail()) {
            return;
        }

        // 如果均有效，则手动提交表单
        $("#registerForm").submit();
    })
})