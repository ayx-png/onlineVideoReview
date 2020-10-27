package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("a"));
    }

    @Test
    void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("bqb","123"));
    }

    @Test
    void saveUser() {
//        System.out.println(userDao.saveUser(new User(null,"a","741")));
    }
}