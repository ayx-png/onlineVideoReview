package model;

public class UserMeeting {
    private Integer id;
    private String username;
    private Integer uid;
    private Integer meetingID;
    private String appID;
    private String token;
    private String audio;
    private String video;

    public UserMeeting(){

    }

    public UserMeeting(String username, Integer uid, Integer meetingID, String appID, String token, String audio, String video){
        this.username = username;
        this.uid = uid;
        this.meetingID = meetingID;
        this.appID = appID;
        this.token = token;
        this.audio = audio;
        this.video = video;
    }

    public String getUsername() { return username; }
    public Integer getUid() { return uid; }
    public Integer getMeetingID() { return meetingID; }
    public String getAppID() { return appID; }
    public String getToken() { return token; }
    public String getAudio() { return audio; }
    public String getVideo() { return video; }

    public void setUsername(String username) { this.username = username; }
    public void setUid(Integer uid) { this.uid = uid; }
    public void setMeetingID(Integer meetingID) { this.meetingID = meetingID; }
    public void setAppID(String appID) { this.appID = appID; }
    public void setToken(String token) { this.token = token; }
    public void setAudio(String audio) { this.audio = audio; }
    public void setVideo(String video) { this.video = video; }
}
