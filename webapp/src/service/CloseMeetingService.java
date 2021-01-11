package service;

import mapper.MeetingMapper;
import mapper.ProjectMapper;
import mapper.UserMapper;
import mapper.UserMeetingMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class CloseMeetingService {

    public MessageModel closeMeeting(String username, String admin, int meetingID) {
        MessageModel messageModel = new MessageModel();

        // 判断是否为主持人
        SqlSession session = GetSqlSession.createSqlSession();

        if(username.equals(admin)){ // 主持人
            // 所有与主持人相同meetingID的userMeeting删除
            UserMeetingMapper userMeetingMapper = session.getMapper(UserMeetingMapper.class);
            userMeetingMapper.deleteUserMeetingByMeetingID(meetingID);
            session.commit();

            // 主持人相同meetingID的Meeting删除
            MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
            meetingMapper.deleteMeetingByMeetingID(meetingID);
            session.commit();

            // 更新projects_table该项目meetingID
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            Project project = projectMapper.queryProjectByProjectHost(admin);
            project.setMeetingID(0);
            projectMapper.updateProject(project);
            session.commit();

            // users_table中所有用户的meeting字段删除
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User[] users = userMapper.queryUserByProject(project.getProjectID());
            for(int i=0; i<users.length; i++){
                users[i].setMeeting(0);
                userMapper.updateMeeting(users[i]);
                session.commit();
            }
        }else{ // 普通用户
            // 删除个人userMeeting信息
            UserMeetingMapper userMeetingMapper = session.getMapper(UserMeetingMapper.class);
            userMeetingMapper.deleteUserMeetingByUsername(username);
            session.commit();

            // 更新users_table中该用户的meeting字段
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.queryUserByUsername(username);
            user.setMeeting(0);
            userMapper.updateMeeting(user);
            session.commit();
        }

        return messageModel;
    }
}
