package test;

import agoraMedia.RtcTokenBuilder;

public class UpdateToken {
    public void updateToken(){
        /*
        Meeting meeting = new Meeting();
        meeting.setAdmin("test");
        meeting.setTopic("token update test");
        meeting.setMemberNum(2);
        meeting.setTimeInMinutes(10);
        meeting.setToken("");
        meeting.setAdminID(10);

        SqlSession session = GetSqlSession.createSqlSession();
        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        meetingMapper.insertMeeting(meeting);
        System.out.println("before" + meeting.getToken());
*/
        String appId = "730a447ceec841d28133f2415810742b";
        String appCertificate = "7b0418bbd36b402eb1e32f3ae7a6a646";
        String channelName = "test";
        int uid = 34;
        int privilegeTs = (int)(System.currentTimeMillis() / 1000 + 200 * 60);
        RtcTokenBuilder token = new RtcTokenBuilder();
        String result = token.buildTokenWithUid(appId, appCertificate, channelName, uid, RtcTokenBuilder.Role.Role_Publisher, privilegeTs);
        System.out.println("token:" + result);
        System.out.println("channel:"+channelName);
    }

    public static void main(String [] args) throws Exception{
        UpdateToken uT = new UpdateToken();
        uT.updateToken();
    }
}
