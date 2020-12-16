package model;

/**
 * User用户实体类
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String companyName;
    private String phoneNumber;
    private String mail;
    private int project;
    private int meeting;
    private String authority = "user";

    public User() {

    }

    public User(int id, String username, String password, String companyName, String phoneNumber, String mail, int project, int meeting, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.project = project;
        this.meeting = meeting;
        this.authority = authority;
    }

    /*User参数的 getter 和 setter方法*/
    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getCompanyName() { return companyName; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getMail() { return mail; }

    public int getProject() { return project; }

    public int getMeeting() { return meeting; }

    public String  getAuthority() { return authority; }


    public void setId(int id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setMail(String mail) { this.mail = mail; }

    public void setProject(int project) { this.project = project; }

    public void setMeeting(int meeting) { this.meeting = meeting; }

    public void setAuthority(String authority) { this.authority = authority; }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", company='" + companyName + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
