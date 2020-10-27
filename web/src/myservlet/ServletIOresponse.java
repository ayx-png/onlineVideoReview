package myservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletIOresponse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置服务器为UTF-8,默认为ISO-8859-1
        response.setCharacterEncoding("UTF-8");
        //设置浏览器使用字符集，默认GBK
        response.setHeader("Content-type","text/html;charset-UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("hello你好");

    }
}
