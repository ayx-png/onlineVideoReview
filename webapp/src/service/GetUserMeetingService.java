package service;

import mapper.UserMeetingMapper;
import model.UserMeeting;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class GetUserMeetingService {

    public MessageModel getUserMeeting(String username) {
        MessageModel messageModel = new MessageModel();

        SqlSession session = GetSqlSession.createSqlSession();
        UserMeetingMapper userMeetingMapper = session.getMapper(UserMeetingMapper.class);
        UserMeeting userMeeting = userMeetingMapper.queryUserMeetingByUsername(username);
        session.commit();

        if(userMeeting == null){
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在该会议");

            return messageModel;
        }
        System.out.println("userMeeting" + userMeeting);

        messageModel.setObject(userMeeting);
        return messageModel;
    }
}
