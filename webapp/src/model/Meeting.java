package model;

/**
 * Meeting会议实体类
 */
public class Meeting {
    private Integer meetingID;  // channelName
    private String  admin;
    private Integer adminID;
    private String topic;
    private Integer memberNum;
    private Integer timeInMinutes;
    private String appID = "7d8c0fb0b109423bbb590d9400abddb0";
    private String appCertificate = "e9b8c6c110b74f7cbb87b3b4611d6d9e";
    /*    private Integer minute;
        private Boolean audio;
        private Boolean video;
    */
    public Meeting(){

    }

    public Meeting(String admin, Integer adminID, String topic, Integer memberNum, Integer timeInMinutes){
//        this.meetingID = meetingID;
        this.admin = admin;
        this.adminID = adminID;
        this.topic = topic;
        this.memberNum = memberNum;
        this.timeInMinutes = timeInMinutes;
        this.appID = "7d8c0fb0b109423bbb590d9400abddb0";
        this.appCertificate = "e9b8c6c110b74f7cbb87b3b4611d6d9e";
/*        this.minute = minute;
        this.audio = audio;
        this.video = video;
*/    }


    public Integer getMeetingID() { return meetingID; }
    public String getAdmin() { return admin; }
    public Integer getAdminID() { return adminID; }
    public String getTopic() { return topic; }
    public Integer getMemberNum() { return memberNum; }
    public Integer getTimeInMinutes() { return timeInMinutes; }
    public String getAppID() { return appID; }
    public String getAppCertificate() { return appCertificate; }
    /*    public Integer getMinute() { return minute; }
        public Boolean getAudio() { return audio; }
        public Boolean getVideo() { return video; }

    public void setMeetingID(Integer meetingID) { this.meetingID = meetingID; }*/
    public void setAdmin(String admin) { this.admin = admin; }
    public void setAdminID(Integer adminID) { this.adminID = adminID; }
    public void setTopic(String topic) { this.topic = topic; }
    public void setMemberNum(Integer memberNum) { this.memberNum = memberNum; }
    public void setTimeInMinutes(Integer timeInMinutes) { this.timeInMinutes = timeInMinutes; }
/*    public void setMinute(Integer minute) { this.minute = minute; }
    public void setAudio(Boolean audio) { this.audio = audio; }
    public void setVideo(Boolean video) { this.video = video; }
*/
}



