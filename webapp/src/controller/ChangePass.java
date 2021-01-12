package controller;

import model.User;
import model.valueObject.MessageModel;
import service.ChangePassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changePass")
public class ChangePass extends HttpServlet {
    private ChangePassService changePassService = new ChangePassService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String newPasswordConfirm = request.getParameter("newPasswordConfirm");

        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        MessageModel messageModel = changePassService.changePass(username, newPassword, newPasswordConfirm);

        if(messageModel.getCode() == 1){ // 成功
            //将消息模型中的新的用户信息设置到session作用域中，重定向到userSpace.jsp页面
            request.getSession().setAttribute("user", messageModel.getObject());
            response.sendRedirect("userSpace.jsp");
        }
        else{ // 失败
            //将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,login.jsp
            request.setAttribute("changePassMessageModel", messageModel);
            request.getRequestDispatcher("infoChange.jsp").forward(request, response);
        }
    }
}
