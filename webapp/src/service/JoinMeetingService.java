package service;

import agoraMedia.RtcTokenBuilder;
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

public class JoinMeetingService {
    public MessageModel joinMeeting(User user, Integer meetingID, String audio, String video) {
        MessageModel messageModel = new MessageModel();

        if(meetingID == null || meetingID == 0){
            messageModel.setMsg("会议号不能为空！");
            messageModel.setCode(0);

            return messageModel;
        }

        SqlSession session = GetSqlSession.createSqlSession();

        if(user.getProjectIn() == 0){
            messageModel.setMsg("用户所在企业尚未被审批！");
            messageModel.setCode(0);

            return messageModel;
        }
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectID(user.getProject());
        if(project.getMeetingID() != meetingID || project.getMeetingID() == 0){
            messageModel.setCode(0);
            messageModel.setMsg("您输入的会议号不正确或会议尚未创建！");

            return messageModel;
        }

        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        Meeting meeting = meetingMapper.queryMeetingByMeetingID(meetingID);


        // 根据uid生成token
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = (int)(System.currentTimeMillis() / 1000 + meeting.getTimeInMinutes() * 60);
        String channel = Integer.toString(meetingID);
        String token_result = token.buildTokenWithUid(meeting.getAppID(), meeting.getAppCertificate(), channel, user.getId(), RtcTokenBuilder.Role.Role_Publisher, timestamp);

        UserMeeting userMeeting = new UserMeeting();
        userMeeting.setUsername(user.getUsername());
        userMeeting.setUid(user.getId());
        userMeeting.setMeetingID(meetingID);
        userMeeting.setAppID(meeting.getAppID());
        userMeeting.setToken(token_result);
        userMeeting.setAudio(audio);
        userMeeting.setVideo(video);

        UserMeetingMapper userMeetingMapper = session.getMapper(UserMeetingMapper.class);
        userMeetingMapper.insertUserMeeting(userMeeting);
        session.commit();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        user.setMeeting(meetingID);
        userMapper.updateMeeting(user);
        session.commit();

        return messageModel;
    }
}
