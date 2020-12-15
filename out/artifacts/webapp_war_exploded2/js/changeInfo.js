function checkCompanyName() {
    let companyName = $("#newCompanyName").val();
    if(companyName == null || companyName.trim() === ""){
        $("#newCompanyName").css("border", "1px solid red");
        $(".infoErrMsg").html("企业名不能为空");
        return false;
    }
    return true;

}
//验证手机号
function checkPhoneNumber() {
    //1.获取手机号
    let phoneNumber = $("#newPhoneNumber").val();
    //2.定义正则
    let reg_phoneNumber = /^1(3([0-35-9]\d|4[1-8])|4[14-9]\d|5([0-35689]\d|7[1-79])|66\d|7[2-35-8]\d|8\d{2}|9[13589]\d)\d{7}$/;

    //3.判断，给出提示信息
    let flag = reg_phoneNumber.test(phoneNumber);
    if (flag) {
        //密码合法
        $("#newPhoneNumber").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#newPhoneNumber").css("border", "1px solid red");
        $(".infoErrMsg").html("请输入有效手机号");
    }
    return flag;
}
//检查邮箱
function checkMail() {
    //1.获取邮箱
    let mail = $("#newMail").val();
    //2.定义正则
    let reg_mail = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;

    //3.判断，给出提示信息
    let flag = reg_mail.test(mail);
    if (flag) {
        //密码合法
        $("#newMail").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#newMail").css("border", "1px solid red");
        $(".infoErrMsg").html("请输入有效邮箱");
    }
    return flag;
}
//检查密码
function checkPassword() {
    //1.获取密码值
    let password = $("#newPassword").val();
    //2.定义正则
    let reg_password = /^\w{3,20}$/;

    //3.判断，给出提示信息
    let flag = reg_password.test(password);
    if (flag) {
        //密码合法
        $("#newPassword").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#newPpassword").css("border", "1px solid red");
        $(".passErrMsg").html("密码保持3到20位");
    }
    return flag;
}

//检查确认密码
function checkPasswordConf() {
    //判断确认密码和密码是否一致
    if( $("#newPasswordConfirm").val() != $("#newPassword").val() ){
        $(".passErrMsg").html("两次密码不一致");
        return false;
    }
    return true;
}
$(document).ready(function(){
    $("#infoChangeBtn").click(function(){
        if(!checkPhoneNumber()){
            return;
        }
        if(!checkMail()){
            return;
        }
        if(!checkMail()){
            return;
        }

        $("#infoForm").submit();
    })
    $("#passChangeBtn").click(function(){
        if(!checkPassword()){
            return;
        }
        if(!checkPasswordConf()){
            return;
        }

        $("#passChangeForm").submit();
    })
});