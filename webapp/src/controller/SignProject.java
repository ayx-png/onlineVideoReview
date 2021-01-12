package controller;

import model.User;
import model.valueObject.MessageModel;
import service.SignProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signProject")
public class SignProject extends HttpServlet {
    private SignProjectService signProjectService = new SignProjectService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String projectName = request.getParameter("projectName");

        User user = (User) request.getSession().getAttribute("user");

        MessageModel messageModel = signProjectService.signProject(user, projectName);

        if(messageModel.getCode() == 1){ // 成功
            //将消息模型中的新的信息设置到session作用域中，重定向到projectSign.jsp页面
            request.getSession().setAttribute("projectSignMessageModel", messageModel);
            response.sendRedirect("projectSign.jsp");
        }
        else{ // 失败
            //将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,projectSign.jsp
            request.setAttribute("projectSignMessageModel", messageModel);
            request.getRequestDispatcher("projectSign.jsp").forward(request, response);
        }
    }
}
