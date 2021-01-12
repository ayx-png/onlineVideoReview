package service;

import mapper.UserMapper;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class ChangePassService {
    public MessageModel changePass(String username, String newPassword, String newPasswordConfirm) {
        MessageModel messageModel = new MessageModel();

        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByUsername(username);

        if(user == null){
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在！");

            return messageModel;
        }

        if(!newPassword.equals(newPasswordConfirm)){
            messageModel.setCode(0);
            messageModel.setMsg("两次密码不一致！");

            return messageModel;
        }

        user.setPassword(newPassword);
        userMapper.updateUserPass(user);
        session.commit();

        messageModel.setObject(user);

        return messageModel;
    }
}
