package mapper;

import model.Meeting;

public interface MeetingMapper {
    /**
     * 根据会议ID查询信息如果返回null则不存在该会议
     * @param admin
     * @return
     */
    public Meeting queryMeetingByAdmin(String admin);

    /**
     * 保存一个会议
     * @param meeting
     * @return
     */
    public int insertMeeting(Meeting meeting);

    /**
     * meetingID查询会议
     * @param meetingID
     * @return
     */
    public Meeting queryMeetingByMeetingID(Integer meetingID);

    /**
     * 按会议ID删除会议
     * @param meetingID
     */
    public void deleteMeetingByMeetingID(int meetingID);

}
