package service;

import mapper.UserMapper;
//import mapper.impl.UserMapperImpl;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;
import utils.StringUtil;

/**
 * 业务逻辑
 */
public class UserService{

    /**
     * 用户登录
         1. 参数的非空判断
            -如果参数为空
                状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
         2. 调用dao（mapper）层的查询方法，通过用户名查询用户对象
         3. 判断用户对象是否为空
            -如果为空，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
         4. 判断数据库中查询到的用户密码是否与前端传入的密码是否相同
            -如果不相等，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
         5. 登陆成功，将成功状态、提示信息、用户对象设置消息模型对象，返回return
     * @param username
     * @param password
     * @return
     */
    public MessageModel userLogin(String username, String password) {
        MessageModel messageModel = new MessageModel();

        // 回显对象
        User u= new User();
        u.setUsername(username);
        u.setPassword(password);
        messageModel.setObject(u);

        // 1. 参数的非空判断
        if(StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){ //用户名、密码任意为空
            // 状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户名和密码不能为空！");

            return messageModel;
        }
        // 2. 调用dao（mapper）层的查询方法，通过用户名查询用户对象
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByUsername(username);

        //  3. 判断用户对象是否为空
        if(user == null){
            // 状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在！");

            return messageModel;
        }

        // 4. 判断数据库中查询到的用户密码是否与前端传入的密码是否相同
        if(!password.equals(user.getPassword())){
            // 如果不相等，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户密码不正确！");
        }

        // 5. 登陆成功，将成功状态、提示信息、用户对象设置消息模型对象，返回return
        messageModel.setObject(user);

        return messageModel;
    }
}
