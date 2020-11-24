package test;

import mapper.UserMapper;
import model.User;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class getSessionTest {
    public static  void  main(String[] args){
        // 获取sqlSession对象
        SqlSession session = GetSqlSession.createSqlSession();
        // 得到对应的Mapper
        UserMapper userMapper = session.getMapper(UserMapper.class);
        // 调用方法，返回用户对象
        User user = userMapper.queryUserByUsername("mcc1");
        System.out.println(user);
    }
}
