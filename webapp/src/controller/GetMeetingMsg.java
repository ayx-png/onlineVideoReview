package controller;

import model.User;
import model.valueObject.MessageModel;
import net.sf.json.JSONObject;
import service.GetMeetingMsgService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getMeetingMsg")
public class GetMeetingMsg  extends HttpServlet {
    // 实例化getMeetingMsgService对象
    private GetMeetingMsgService getMeetingMsgService = new GetMeetingMsgService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();

        MessageModel messageModel = getMeetingMsgService.getMeetingMessage(username);

        JSONObject jsonObj = JSONObject.fromObject(messageModel.getObject());
        response.getWriter().write(jsonObj.toString());
    }
}
