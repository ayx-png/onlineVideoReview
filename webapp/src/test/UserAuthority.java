package test;

import model.User;

public class UserAuthority {
    public void userAuthority(){
        User u = new User();
        System.out.println(u.getAuthority());
    }

    public static void main(String [] args) throws Exception{
        UserAuthority UA = new UserAuthority();
        UA.userAuthority();
    }

}
