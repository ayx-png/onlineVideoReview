package service;

import mapper.ProjectMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class GetUserProjectService {
    public MessageModel getUserProject(User user) {
        MessageModel messageModel = new MessageModel();

        SqlSession session = GetSqlSession.createSqlSession();
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectID(user.getProject());

        if(project != null){
            messageModel.setObject(project);
        }
        else {
            messageModel.setCode(0);
            messageModel.setMsg("该用户不存在已报名或创建的项目！");
        }
        return messageModel;
    }
}
