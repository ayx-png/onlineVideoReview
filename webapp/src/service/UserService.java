package service;
import model.User;

/**
 * 业务逻辑
 */

public interface UserService {
    /**
     * 注册用户
     */
    public void register(User user);
    public User login(User user);
    public boolean existUsername(String username);
}
