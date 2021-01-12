package service;

import mapper.ProjectMapper;
import mapper.UserMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class SignProjectService {
    /**
     * 普通用户报名评审项目
     *      1. 判断项目名合法性
     *      2. 查看用户权限
     *          若authority非user，则不能报名评审项目
     *      3. 查看用户是否已报名评审项目
     *          若已报名，则不能再再报名其他项目
     *      4. 在数据库查询项目是否存在
     *      若存在，获取项目ID
     *      5. 在用户表中添加评审项目ID
     * @param user
     * @param projectName
     * @return
     */
    public MessageModel signProject(User user, String projectName) {
        MessageModel messageModel = new MessageModel();

        // 1. 判断项目名合法性
        if(projectName.equals("")){
            messageModel.setCode(0);
            messageModel.setMsg("项目名不能为空！");

            return messageModel;
        }

        // 2. 查看用户权限
        if(!user.getAuthority().equals("user")){
            messageModel.setCode(0);
            messageModel.setMsg("只有普通用户才能报名评审会议！");

            return messageModel;
        }

        // 3. 查看用户是否已报名评审项目
        if(user.getProject() != 0){
            messageModel.setCode(0);
            messageModel.setMsg("您已报名其他评审项目，不可报名其他项目");

            return messageModel;
        }

        // 4. 在数据库查询项目是否存在
        SqlSession session = GetSqlSession.createSqlSession();
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectName(projectName);
        session.commit();
        if(project == null){
            messageModel.setCode(0);
            messageModel.setMsg("您报名的项目不存在！");

            return messageModel;
        }

        // 5. 在用户表中添加评审项目信息
        UserMapper userMapper = session.getMapper(UserMapper.class);
        user.setProject(project.getProjectID());
        userMapper.updateProject(user);
        session.commit();

        messageModel.setObject(user);
        messageModel.setMsg("报名成功！");
        return messageModel;
    }
}
