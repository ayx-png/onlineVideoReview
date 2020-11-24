package test;

import mapper.MeetingMapper;
import model.Meeting;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class UpdateToken {
    public void updateToken(String token){
        Meeting meeting = new Meeting();
        meeting.setAdminID("test");
        meeting.setTopic("token update test");
        meeting.setMemberNum(2);
        meeting.setTimeInMinutes(10);
        meeting.setToken("");

        SqlSession session = GetSqlSession.createSqlSession();
        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        meetingMapper.insertMeeting(meeting);
        System.out.println("before" + meeting.getToken());

        meeting.setToken(token);
        meetingMapper.updateMeeting(meeting);
        System.out.println("after:" + meeting.getToken());
    }

    public static void main(String [] args) throws Exception{
        UpdateToken uT = new UpdateToken();
        String token = "test";
        uT.updateToken(token);
    }
}
