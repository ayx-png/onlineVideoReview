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

/**
 * 业务逻辑
 */
public class MeetingCreateService {
    /**
     * 1. 参数合法性判断
            -如果参数不合法
                状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
       2. 调用dao（mapper）层的查询方法，通过channel查询会议对象
       3. 判断会议是否存在
            --若已经存在，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
       4. 创建成功， 将成功状态、提示信息、用户对象设置消息模型对象
       5. 生成会议token并更新至会议对象
       6. 数据库生成该会议成员表，并将会议管理员加入表
       7. 将会议数据存入数据库，会议成员存入数据库表，返回return
     * @param topic
     * @param memberNum
     * @param hour
     * @param minute
     * @param audio
     * @param video
     * @return
     */
    public MessageModel createMeeting(String admin, String topic, Integer memberNum, Integer hour, Integer minute, String audio, String video) {
        MessageModel messageModel = new MessageModel();

        // 回现对象
        Meeting m = new Meeting();
        m.setAdmin(admin);
        m.setTopic(topic);
        m.setMemberNum(memberNum);
        m.setTimeInMinutes(hour * 60 + minute);
/*        m.setMinute(minute);
        m.setAudio(audio);
        m.setVideo(video);
*/
        // 1. 参数合法性判断
        if(memberNum <= 0){
            messageModel.setCode(0);
            messageModel.setMsg("会议人数不能为0");
            return messageModel;
        }
        if(hour==0 && minute==0){
            messageModel.setCode(0);
            messageModel.setMsg("会议时间不能为0");
            return messageModel;
        }

        // 2. 调用dao（mapper）层的查询方法，
        SqlSession session = GetSqlSession.createSqlSession();
        MeetingMapper meetingMapper = session.getMapper(MeetingMapper.class);
        Meeting meeting = meetingMapper.queryMeetingByAdmin(admin);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByUsername(admin);

        // 3. 判断会议是否存在
        if(meeting != null){
            // 若已经存在，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("已存在你管理的会议，不可再次创建新的会议！");
            return messageModel;
        }

        // 4.将会议信息存入数据库表并获取会议ID
        System.out.println(user.getId());
        m.setAdminID(user.getId());
        meetingMapper.insertMeeting(m);
        System.out.println(m);
        session.commit();

        // 5. 生成会议token并更新至会议对象
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = (int)(System.currentTimeMillis() / 1000 + m.getTimeInMinutes() * 60);
        String channel = Integer.toString(m.getMeetingID());
        String result = token.buildTokenWithUid(m.getAppID(), m.getAppCertificate(), channel, user.getId(), RtcTokenBuilder.Role.Role_Publisher, timestamp);
        System.out.println("token:" + result);
        System.out.println("channel:" + channel);

        // userMeetingMsg_table更新
        UserMeeting userMeeting = new UserMeeting();
        userMeeting.setUsername(user.getUsername());
        userMeeting.setUid(user.getId());
        userMeeting.setMeetingID(m.getMeetingID());
        userMeeting.setAppID(m.getAppID());
        userMeeting.setToken(result);
        userMeeting.setAudio(audio);
        userMeeting.setVideo(video);
        UserMeetingMapper userMeetingMapper = session.getMapper(UserMeetingMapper.class);
        userMeetingMapper.insertUserMeeting(userMeeting);
        session.commit();

        // projects_table存入项目相关会议号
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectHost(user.getUsername());
        project.setMeetingID(m.getMeetingID());
        projectMapper.updateProject(project);
        session.commit();

        // 6. 用户表中更新主持人meeting字段会议成员表
        user.setMeeting(m.getMeetingID());
        userMapper.updateMeeting(user);
        session.commit();


        // 7. 将会议数据存入数据库，会议成员存入数据库表，返回return
        messageModel.setMsg("会议创建成功！");
        //messageModel.setObject(m);
        return  messageModel;
    }
}
