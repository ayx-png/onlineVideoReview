package myservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(name = "Servlet1")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1在post");
        request.setCharacterEncoding("UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1在get");
        String username=request.getParameter("username");
//        String[] userhobby=request.getParameterValues("hobby");
        System.out.println("用户姓名为"+username);
//        System.out.println("用户的爱好为"+ Arrays.asList(userhobby));//注意多选时需要数组接收
        request.setAttribute("key","柜台1盖的章");
        /**
         * servlet2怎么走
         */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Servlet2");//url-pattern
        requestDispatcher.forward(request,response);
    }
}
