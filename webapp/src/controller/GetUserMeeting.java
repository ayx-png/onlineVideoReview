package controller;

import model.User;
import model.valueObject.MessageModel;
import net.sf.json.JSONObject;
import service.GetUserMeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUserMeeting")
public class GetUserMeeting extends HttpServlet {
    GetUserMeetingService getUserMeetingService = new GetUserMeetingService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        MessageModel messageModel = getUserMeetingService.getUserMeeting(username);

        if(messageModel.getCode() != 0){ // 成功
            JSONObject jsonObj = JSONObject.fromObject(messageModel.getObject());
            response.getWriter().write(jsonObj.toString());
        }
    }
}
