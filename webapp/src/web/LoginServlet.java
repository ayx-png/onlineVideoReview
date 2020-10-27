package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserServiceImpl();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User loginUser = userService.login(new User(null, username, password));
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
//            request.setAttribute("msg","用户或密码错误！");
//            request.setAttribute("username", username);
            //   跳回登录页面
            request.getRequestDispatcher("/index.html").forward(request, response);
        } else {
            // 登录 成功
            //跳到成功页面pre-test.html
            request.getRequestDispatcher("/pre-test.html").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
