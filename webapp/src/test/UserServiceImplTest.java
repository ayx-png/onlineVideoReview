package test;

import org.junit.jupiter.api.Test;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

class UserServiceImplTest {
    UserService userService=new UserServiceImpl();

    @Test
    void register() {
//        userService.register(new User(null,"bqb2","82456)"));
    }

    @Test
    void login() {
        System.out.println(userService.login(new User(null,"bqb","123")));
    }

    @Test
    void existUsername() {

    }
}