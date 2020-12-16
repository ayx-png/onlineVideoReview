package service;

import mapper.ProjectMapper;
import mapper.UserMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class CreateProjectService {

    public MessageModel createProject(User user, String projectName) {
        MessageModel messageModel = new MessageModel();

        Project p = new Project();
        p.setProjectName(projectName);
        p.setProjectHost(user.getUsername());

        if(projectName.equals("")){
            messageModel.setCode(0);
            messageModel.setMsg("项目名称不能为空！");

            return messageModel;
        }
        if(!user.getAuthority().equals("host")){
            messageModel.setCode(0);
            messageModel.setMsg("您无权限创建项目！");

            return messageModel;
        }
        if(user.getProject() > 0){
            messageModel.setCode(0);
            messageModel.setMsg("已存在您管理的项目！");

            return messageModel;
        }

        SqlSession session = GetSqlSession.createSqlSession();
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectName(projectName);
        session.commit();

        if(project != null){
            messageModel.setCode(0);
            messageModel.setMsg("该项目名已存在！");

            return messageModel;
        }

        UserMapper userMapper = session.getMapper(UserMapper.class);
        user.setAuthority("host");
        userMapper.updateUserAuthority(user);
        session.commit();

        projectMapper.insertProject(p);
        session.commit();

        messageModel.setObject(user);
        messageModel.setMsg(projectName + "创建成功！");
        return messageModel;
    }
}
