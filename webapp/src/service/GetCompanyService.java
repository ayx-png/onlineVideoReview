package service;

import model.User;
import model.valueObject.MessageModel;

public class GetCompanyService {
    public MessageModel getCompany(User[] users, String username) {
        MessageModel messageModel = new MessageModel();

//        SqlSession session = GetSqlSession.createSqlSession();
//        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
//        Project project = projectMapper.queryProjectByProjectHost(username);
//
//        int projectID = project.getProjectID();
//        UserMapper userMapper = session.getMapper(UserMapper.class);
//        User[] users = userMapper.queryUserByProject(projectID);
//        session.commit();

        String[] companies = new String[17];
        if(users == null || users.length == 0){
            messageModel.setCode(0);
            messageModel.setMsg("未有企业报名评审项目！");

            return messageModel;
        }
        else {
            for(int i=0; i<users.length; i++ ) {
                if(!users[i].getUsername().equals(username)){
                    companies[i] = users[i].getCompanyName();
                }
            }
        }

        messageModel.setObject(companies);
        return messageModel;
    }
}
