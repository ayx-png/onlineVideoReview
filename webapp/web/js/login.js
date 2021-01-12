$(document).ready(function () {

    //-------------验证用户名和密码是否为空，为后端提供非空字符串---------
    $("#enter").click(function () {
        //获取用户名和密码
        let uname = $("#username").val();
        let pwd = $("#password").val();
        //用户名和密码是否为空
        if (isEmpty(uname)) {
            $("#errorMsg").html("用户名不能为空");
            return;
        }
        if (isEmpty(pwd)) {
            $("#errorMsg").html("密码不能为空");
            return;
        }
        //如果均不为空，则手动提交表单
        $("#loginForm").submit();
    })

    /**
     * 判断字符串是否为空
     *      为空，返回true
     *      不为空，返回false
     * @param str
     * @returns {boolean}
     */
    function isEmpty(str) {
        return str == null || str.trim() === "";
    }
});