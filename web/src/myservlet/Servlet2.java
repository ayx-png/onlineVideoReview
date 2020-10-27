package myservlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext context=getServletConfig().getServletContext();
//        String password=context.getInitParameter("password");
//        System.out.println(password);
//        System.out.println("当前工程路径"+context.getContextPath());

        String username=request.getParameter("username");
        System.out.println("柜台2查看材料"+username);
        Object key=request.getAttribute("key");
        System.out.println("柜台2查看柜台1的章"+key);
        System.out.println("柜台2开始处理业务");
    }
}
