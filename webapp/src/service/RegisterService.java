package service;

import mapper.UserMapper;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;
import utils.StringUtil;

public class RegisterService {
    /**
     * 1. 参数合法性判断
           -如果参数不合法
               状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
       2. 调用dao（mapper）层的查询方法，通过用户名查询用户对象
       3. 判断用户对象是否存在
           -若已经存在，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
       4. 注册成功， 将成功状态、提示信息、用户对象设置消息模型对象
       5. 将用户数据存入数据库，返回return
     * @param username
     * @param password
     * @param passwordConf
     * @param companyName
     * @param phoneNumber
     * @param mail
     * @return
     */
    public MessageModel userRegister(String username, String password, String passwordConf, String companyName, String phoneNumber, String mail) {
        MessageModel messageModel = new MessageModel();

        // 回显对象
        User u= new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setCompanyName(companyName);
        u.setPhoneNumber(phoneNumber);
        u.setMail(mail);
        messageModel.setObject(u);

        //1. 参数合法性判断
        if(!StringUtil.isLegal(username) || !StringUtil.isLegal(password)){
            messageModel.setCode(0);
            messageModel.setMsg("用户名和密码保持3-20位！");

            return messageModel;
        }
        if(!password.equals(passwordConf)){
            messageModel.setCode(0);
            messageModel.setMsg("密码不一致！");

            return messageModel;
        }
        if(StringUtil.isEmpty(companyName)){
            messageModel.setCode(0);
            messageModel.setMsg("企业名不能为空！");

            return messageModel;
        }
        if(!StringUtil.isPhoneNumber(phoneNumber)){
            messageModel.setCode(0);
            messageModel.setMsg("请输入有效手机号！");

            return messageModel;
        }
        if(!StringUtil.isMail(mail)){
            messageModel.setCode(0);
            messageModel.setMsg("请输入有效邮箱！");

            return messageModel;
        }

        // 2. 调用dao（mapper）层的查询方法，通过用户名查询用户对象
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByUsername(username);

        // 3. 判断用户对象是否存在
        if(user != null){
            // 若已经存在，状态码、提示信息、回显数据设置到消息模型对象中，返回消息模型对象
            messageModel.setCode(0);
            messageModel.setMsg("用户名已被注册，请使用其他用户名");
            return messageModel;
        }

        // 4. 将用户数据存入数据库
        userMapper.insertUser(u);
        System.out.println(u);
        session.commit();

        // 5. 注册成功， 将成功状态、提示信息、用户对象设置消息模型对象，返回return
        messageModel.setMsg(username + "注册成功，请登录！");
        // messageModel.setObject(u);
        return messageModel;
    }
}
