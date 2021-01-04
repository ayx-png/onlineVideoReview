package service;

import mapper.MeetingMapper;
import mapper.ProjectMapper;
import mapper.UserMapper;
import mapper.UserMeetingMapper;
import model.Meeting;
import model.Project;
import model.User;
import model.UserMeeting;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

import java.util.ArrayList;
import java.util.List;

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

        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        Meeting meeting = meetingMapper.queryMeetingByMeetingID(userMeeting.getMeetingID());

        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectHost(meeting.getAdmin());
        session.commit();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        User[] users = userMapper.queryUserByProject(project.getProjectID());
        List<String> usersName = new ArrayList<String>();
        for(int i=0; i< users.length; i++){
            usersName.add(users[i].getUsername());
        }

        Object[] returnMsg = {userMeeting, meeting, project, usersName};
        System.out.println("returnMsg:" + returnMsg[2].toString());

        messageModel.setObject(returnMsg);
        return messageModel;
    }
}
