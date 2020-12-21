package mapper;

import model.User;

/**
 * 用户接口类
 */
public interface UserMapper {

    /**
     * 根据用户名返回查询信息如果返回null则没有这个用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码进行查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 修改用户密码
     * @param user
     */
    public void updateUserPass(User user);

    /**
     * 修改权限
     * @param user
     */
    public void updateUserAuthority(User user);

    /**
     * 用户添加项目
     * @param user
     */
    public void updateProject(User user);
}
