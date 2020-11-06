package controller;

import model.valueObject.MessageModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserServlet extends javax.servlet.http.HttpServlet {
    /*
    //doPost() 和 doGet() 可在service()中一起实现
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("get");
        String username=request.getParameter("username");
        System.out.println("用户姓名为"+username);
    }
    */

    // 实例化UserService对象
    private UserService userService = new UserService();

    /**
     * 用户登录
         1. 接受客户端的请求（接收参数：用户名、密码）
         2. 调用service层的方法，返回消息模型对象
         3. 判断消息模型状态码
             -如果状态码为失败
                 将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,index.html
             -如果状态码是成功
                将消息模型中的用户信息设置到session作用域中，重定向到pre-test.html页面,index.html
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受客户端的请求（接收参数：用户名、密码）
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2. 调用service层的方法，返回消息模型对象
        MessageModel messageModel = userService.userLogin(username, password);
        // 3. 判断消息模型状态码
        if(messageModel.getCode() == 1){ // 成功
            //将消息模型中的用户信息设置到session作用域中，重定向到pre-test.html页面,index.html
            request.getSession().setAttribute("user", messageModel.getObject());
            response.sendRedirect("pre-test.html");
        }
        else{ // 失败
            //将消息模型中的用户信息设置到session作用域中，重定向到pre-test.html页面,index.html
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
