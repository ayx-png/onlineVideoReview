package controller;

import model.valueObject.MessageModel;
import service.CloseMeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/closeMeeting")
public class CloseMeeting extends HttpServlet {
    private CloseMeetingService closeMeetingService = new CloseMeetingService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String admin = request.getParameter("admin");
        int meetingID = Integer.parseInt(request.getParameter("meetingID"));
        MessageModel messageModel = closeMeetingService.closeMeeting(username, admin, meetingID);
        System.out.println("before response");

        if(messageModel.getCode() == 1){
            System.out.println("in response");
            response.sendRedirect("userSpace.jsp");
        }
    }
}
