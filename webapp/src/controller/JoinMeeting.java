package controller;

import model.User;
import model.valueObject.MessageModel;
import service.JoinMeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/joinMeeting")
public class JoinMeeting extends HttpServlet {
    private JoinMeetingService joinMeetingService = new JoinMeetingService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer meetingID = Integer.valueOf(request.getParameter("meetingID"));
        String audio = request.getParameter("audio");
        String video = request.getParameter("video");

        User user = (User) request.getSession().getAttribute("user");

        MessageModel messageModel = joinMeetingService.joinMeeting(user, meetingID,audio,video);

        if(messageModel.getCode() != 0){ // 成功
            response.sendRedirect("meeting.html");
        }
        else { // 失败
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("joinMeeting.jsp").forward(request,response);
        }
    }

}
