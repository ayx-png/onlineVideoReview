package controller;

import mapper.ProjectMapper;
import mapper.UserMapper;
import model.Project;
import model.User;
import model.valueObject.MessageModel;
import net.sf.json.JSONArray;
import org.apache.ibatis.session.SqlSession;
import service.GetCompanyService;
import utils.GetSqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getCompany")
public class GetCompany extends HttpServlet {
    GetCompanyService getCompanyService = new GetCompanyService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        SqlSession session = GetSqlSession.createSqlSession();
        ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
        Project project = projectMapper.queryProjectByProjectHost(username);

        int projectID = project.getProjectID();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User[] users = userMapper.queryUserByProject(projectID);
        session.commit();

        MessageModel messageModel = getCompanyService.getCompany(users, username);

        if(messageModel.getCode() == 0){

        }
        else{
            int[] projectUsersID = new int[17];
            if(users != null || users.length != 0){
                for(int i=0; i<users.length; i++){
                    projectUsersID[i] = users[i].getId();
//                    System.out.println(projectUsersID[i]);
                }
            }
            request.getSession().setAttribute("projectUsersID", projectUsersID);
            response.setContentType("text/html;charset=UTF-8");
            JSONArray jsonArr = JSONArray.fromObject(messageModel.getObject());
            response.getWriter().write(jsonArr.toString());
        }
    }
}
