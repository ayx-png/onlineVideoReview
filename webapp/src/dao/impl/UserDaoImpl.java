package dao.impl;

import dao.UserDao;
import pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql ="select `id`,`username`,`password` from users where username = ?";
//        System.out.println(queryForOne(User.class,sql,"a"));
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password` from users where username = ? and password = ?";

        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into users(`username`,`password`) values(?,?)";
        return update(sql,user.getUsername(),user.getPassword());
    }
}
