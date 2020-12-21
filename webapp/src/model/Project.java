package model;

public class Project {
    private int projectID;
    private String projectName;
    private String projectHost;
    private int meetingID;

    public Project(){

    }

    public Project(String projectName, String projectHost){
        this.projectName = projectName;
        this.projectHost = projectHost;
    }

    public int getProjectID() { return projectID; }
    public String getProjectName() { return projectName; }
    public String getProjectHost() { return projectHost; }
    public int getMeetingID() { return meetingID; }

    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setProjectHost(String projectHost) { this.projectHost = projectHost; }
    public void setMeetingID(int meetingID) { this.meetingID = meetingID; }
}

