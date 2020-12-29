$(document).ready(function(){

    $("#fastJoinMeeting").click(function(){

        let meetingID = $("#meetingID").val();

        if(isEmpty(meetingID)){
            $(".errMsg").html("会议号不能为空！");
            return false;
        }

        $("#joinMeetingForm").submit();
    })

    function isEmpty(str) {
        return str == null || str.trim() === "";
    }
});