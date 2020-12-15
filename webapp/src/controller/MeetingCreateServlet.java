package controller;

import model.User;
import model.valueObject.MessageModel;
import service.MeetingCreateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/meetingCreate")
public class MeetingCreateServlet extends HttpServlet {
    // 实例化meetingCreateService对象
    private MeetingCreateService meetingCreateService = new MeetingCreateService();
    /**
     * 创建会议
         1. 接收客户端的请求（参数：会议主题、人数、时长、麦克风和摄像头状态）
         2. 调用service层的方法，返回消息模型对象
         3. 判断消息模型状态码
          -如果状态码为失败
              将消息模型对象将消息模型对象设置到request作用域中，请求跳转到登录页面,meetingCreate.jsp
          -如果状态码是成功
              将消息模型中的用户信息设置到session作用域中，重定向到meeting.jsp页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收客户端的请求（参数：会议管理者、会议主题、人数、时长、麦克风和摄像头状态）
        User user = (User) request.getSession().getAttribute("user");
        String admin = user.getUsername();
        String topic = request.getParameter("topic");
        Integer memberNum = Integer.parseInt(request.getParameter("memberNum"));
        Integer hour = Integer.parseInt(request.getParameter("hour"));
        Integer minute = Integer.parseInt(request.getParameter("minute"));
        Boolean audio = Boolean.parseBoolean(request.getParameter("audio"));
        Boolean video = Boolean.parseBoolean(request.getParameter("video"));

        // 2. 调用service层的方法，返回消息模型对象
        MessageModel messageModel = meetingCreateService.createMeeting(admin, topic, memberNum, hour, minute, audio, video);

        // 3. 判断消息模型状态码
        if(messageModel.getCode() == 1){    // 成功
            // 将消息模型中的用户信息设置到session作用域中，重定向到meeting.jsp页面
            request.getSession().setAttribute("message", messageModel.getMsg());
            request.getSession().setAttribute("meeting", messageModel.getObject());
            response.sendRedirect("meeting.html");
        }
        else{   // 失败
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("meetingCreate.jsp").forward(request, response);
        }
    }
}
