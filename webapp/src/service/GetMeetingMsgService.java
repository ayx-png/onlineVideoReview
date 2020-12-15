package service;

import mapper.MeetingMapper;
import model.Meeting;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class GetMeetingMsgService {
    public MessageModel getMeetingMessage(String username) {
        MessageModel messageModel = new MessageModel();

        SqlSession session = GetSqlSession.createSqlSession();
        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        Meeting meeting = meetingMapper.queryMeetingByAdmin(username);
        session.commit();

        if(meeting != null){
            System.out.println(meeting);
            messageModel.setObject(meeting);
        }
        else{
            messageModel.setCode(0);
            messageModel.setMsg("会议不存在");
        }
        return messageModel;
    }
}
