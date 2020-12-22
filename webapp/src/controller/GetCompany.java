package controller;

import model.User;
import model.valueObject.MessageModel;
import net.sf.json.JSONArray;
import service.GetCompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getCompany")
public class GetCompany extends HttpServlet {
    GetCompanyService getCompanyService = new GetCompanyService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        MessageModel messageModel = getCompanyService.getCompany(username);

        response.setContentType("text/html;charset=UTF-8");
        JSONArray jsonArr = JSONArray.fromObject(messageModel.getObject());
        response.getWriter().write(jsonArr.toString());
    }
}
