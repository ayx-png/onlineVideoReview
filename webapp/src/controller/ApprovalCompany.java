package controller;

import model.valueObject.MessageModel;
import service.ApprovalCompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approvalCompany")
public class ApprovalCompany extends HttpServlet {
    ApprovalCompanyService approvalCompanyService = new ApprovalCompanyService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] legalCompanies = request.getParameterValues("companyName");
        System.out.println(legalCompanies);

        int[] projectUsersID = (int[])request.getSession().getAttribute("projectUsersID");
        MessageModel messageModel = approvalCompanyService.approvalCompany(legalCompanies, projectUsersID);

        if(messageModel.getCode() == 1){ // 成功
            //将消息模型中的新的用户信息设置到session作用域中，重定向到userSpace.jsp页面
            request.setAttribute("approvalCompanyMessageModel", messageModel);
            request.getRequestDispatcher("approvalCompany.jsp").forward(request, response);
        }
        else{ // 失败
            //将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,login.jsp
            request.setAttribute("approvalCompanyMessageModel", messageModel);
            request.getRequestDispatcher("approvalCompany.jsp").forward(request, response);
        }
    }
}
