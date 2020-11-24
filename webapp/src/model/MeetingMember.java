package model;

public class MeetingMember {
    private String memberName;
    private Integer meetingID;

    public MeetingMember(){

    }

    public MeetingMember(String memberName, Integer meetingID){
        this.memberName = memberName;
        this.meetingID = meetingID;
    }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public Integer getMeetingID() { return meetingID; }

    public void setMeetingID(Integer meetingID) { this.meetingID = meetingID; }

}
