package service;

import mapper.UserMapper;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;

public class ChangeInfoService {
    public MessageModel changeInfo(String username, String newPhoneNumber, String newMail, String newCompanyName) {
        MessageModel messageModel = new MessageModel();

        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByUsername(username);

        if(user == null){
            messageModel.setCode(0);
            messageModel.setMsg("用户不存在！");

            return messageModel;
        }

        user.setPhoneNumber(newPhoneNumber);
        user.setMail(newMail);
        user.setCompanyName(newCompanyName);
        userMapper.updateUser(user);
        session.commit();

        messageModel.setObject(user);

        return messageModel;
    }
}
