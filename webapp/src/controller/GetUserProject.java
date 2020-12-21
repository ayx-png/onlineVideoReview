package controller;

import model.User;
import model.valueObject.MessageModel;
import net.sf.json.JSONObject;
import service.GetUserProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUserProject")
public class GetUserProject extends HttpServlet {
    private GetUserProjectService getUserProjectService = new GetUserProjectService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        MessageModel messageModel = getUserProjectService.getUserProject(user);

        JSONObject jsonObj = JSONObject.fromObject(messageModel.getObject());
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(jsonObj.toString());
    }
}
