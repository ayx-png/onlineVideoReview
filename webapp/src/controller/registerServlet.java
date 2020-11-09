package controller;

import model.valueObject.MessageModel;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    // 实例化registerService对象
    private RegisterService registerService = new RegisterService();

    /**
     * 1. 接受客户端的请求（接收参数：用户名、密码、 密码确认、企业名、手机号、邮箱）
       2. 调用service层的方法，返回消息模型对象
       3. 判断消息模型状态码
           -如果状态码为失败
               将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,register.jsp
           -如果状态码是成功
               将消息模型中的用户信息设置到session作用域中，重定向到login.jsp页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  1. 接受客户端的请求（接收参数：用户名、密码、 密码确认、企业名、手机号、邮箱）
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConf = request.getParameter("passwordConf");
        String companyName = request.getParameter("companyName");
        String phoneNumber = request.getParameter("phoneNumber");
        String mail = request.getParameter("mail");

        // 2. 调用service层的方法，返回消息模型对象
        MessageModel messageModel = registerService.userRegister(username, password, passwordConf, companyName, phoneNumber, mail);

        // 3. 判断消息模型状态码
        if(messageModel.getCode() == 1){ // 成功
            // 将消息模型中的用户信息设置到session作用域中，请求转发到向到login.jsp页面，并提示注册成功
            request.getSession().setAttribute("message", messageModel.getMsg());
            request.getSession().setAttribute("user", messageModel.getObject());
            response.sendRedirect("login.jsp");
        }
        else{ // 失败
            //将消息模型对象将消息模型对象设置到request作用域中，请求转发到注册页面,register.jsp
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }
}
