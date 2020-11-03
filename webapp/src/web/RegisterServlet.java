package web;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(userService.existUsername(username)){
            request.getRequestDispatcher("/register.html").forward(request,resp);
            System.out.println("用户名已存在");
        }else{
            userService.register(new User(null,username,password));
            request.getRequestDispatcher("/index.html").forward(request,resp);

        }


    }
}
