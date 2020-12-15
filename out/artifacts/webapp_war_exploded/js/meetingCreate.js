/**
 * 检查会议时间是否为0
 * @returns {boolean}
 */
function checkTime() {
    let hour = +$("#hour").val();
    let minute = +$("#minute").val();
    let time = hour * 60 + minute;
    console.log(time);
    let flag = time <= 0;
    if(flag){
        $("#err").html("会议时间不能为0");
    }
    return flag;
}

/**
 * 检查会议人数是否为0
 * @returns {boolean}
 */
function checkMemberNum() {
    let memberNum = +$("#memberNum").val();
    console.log(memberNum);
    let flag =  memberNum <= 0;
    if(flag){
        $("#err").html("会议人数不能为0");
    }
    return flag
}

$(document).ready(function () {
    $("#submitBtn").click(function() {
        if(checkMemberNum()){
            return;
        }
        if(checkTime()){
            return;
        }

        // 手动绑定表单提交事件
        $("#meetingForm").submit();
    })
});