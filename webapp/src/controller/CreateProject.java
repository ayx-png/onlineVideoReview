package controller;

import model.User;
import model.valueObject.MessageModel;
import service.CreateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProject")
public class CreateProject extends HttpServlet {
    private CreateProjectService createProjectService = new CreateProjectService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String projectName = request.getParameter("projectName");

        User user = (User) request.getSession().getAttribute("user");

        MessageModel messageModel = createProjectService.createProject(user, projectName);

        response.setContentType("text/html;charset=UTF-8");
        if(messageModel.getCode() == 1){ // 成功
            //将消息模型中的新的信息设置到session作用域中，重定向到userSpace.jsp页面
            request.getSession().setAttribute("messageModel", messageModel);
            response.sendRedirect("projectCreate.jsp");
        }
        else{ // 失败
            //将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,login.jsp
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("projectCreate.jsp").forward(request, response);
        }
    }
}
