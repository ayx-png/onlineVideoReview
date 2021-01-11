package mapper;

import model.UserMeeting;

public interface UserMeetingMapper {
    public  void insertUserMeeting(UserMeeting userMeeting);

    public UserMeeting queryUserMeetingByUsername(String username);

    public void deleteUserMeetingByMeetingID(int meetingID);

    public void deleteUserMeetingByUsername(String username);
}
