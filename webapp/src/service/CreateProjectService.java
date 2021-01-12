package service;

import mapper.ProjectMapper;
import mapper.UserMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class CreateProjectService {
    /** 评审项目主持人创建项目
     *     1. 接受客户端的请求（评审项目名，判断合法性）
     *     2. 判断用户是否有权限创建项目
     *         -若无权限
     *             通过消息模型对象返回结果（设置状态、提示信息"无权限"、回显数据）
     *                  将消息模型对象设置到request作用域中
     *                  请求跳转到用户中心页面,userSpace.jsp
     *                  return
     *     3. 判断项目名是否已存在
     *         -若已存在：
     *             通过消息模型对象返回结果（设置状态、提示信息、回显数据）
     *                  将消息模型对象设置到request作用域中
     *                  请求跳转到用户中心页面,userSpace.jsp
     *                  return
     *     4. 在数据库中添加数据
     *         -将项目名，主持人信息保存至项目表单
     *         -user表添加项目id
     * @param user
     * @param projectName
     * @return
     */
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

        // 将项目名，主持人信息保存至项目表单
        projectMapper.insertProject(p);
        session.commit();

        // user表添加项目id
        UserMapper userMapper = session.getMapper(UserMapper.class);
        user.setAuthority("host");
        userMapper.updateUserAuthority(user);
        user.setProject(p.getProjectID());
        userMapper.updateProject(user);
        session.commit();

        messageModel.setObject(user);
        messageModel.setMsg(projectName + "创建成功！");
        return messageModel;
    }
}
