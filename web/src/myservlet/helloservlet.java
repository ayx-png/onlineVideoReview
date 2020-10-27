package myservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class helloservlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(servletConfig.getServletName());//获取servlet名字
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet被访问了");
        //类型转换
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        String method=httpServletRequest.getMethod();
        System.out.println(method);
        if("get".equals(method)){
            doGet();
        }else {
            doPost();
        }

    }
    public void doGet(){
        System.out.println("执行get");
    }
    public void doPost(){
        System.out.println("执行post");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
