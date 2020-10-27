package service;
import pojo.User;
public interface UserService {
    /**
     * 注册用户
     */
    public void register(User user);
    public User login(User user);
    public boolean existUsername(String username);
}
