package controller;

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
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String meetingID = request.getParameter("meetingID");
        String audio = request.getParameter("audio");
        String video = request.getParameter("video");

        MessageModel messageModel = JoinMeetingService.joinMeeting(meetingID,audio,video);

        if(messageModel.getCode() != 0){
            response.sendRedirect("meeting.jsp");
        }
        else {
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("joinMeeting.jsp").forward(request,response);
        }
    }

}
