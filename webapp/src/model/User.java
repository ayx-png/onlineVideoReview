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

    public User() {

    }

    public User(int id, String username, String password, String companyName, String phoneNumber, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    /*User参数的 getter 和 setter方法*/
    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getCompanyName() { return companyName; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getMail() { return mail; }

    public void setId(int id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setMail(String mail) { this.mail = mail; }

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
