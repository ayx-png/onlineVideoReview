package service;

import mapper.UserMapper;
import model.User;
import model.valueObject.MessageModel;
import org.apache.ibatis.session.SqlSession;
import utils.GetSqlSession;


public class ApprovalCompanyService {
    public MessageModel approvalCompany(String[] legalCompanies, int[] projectUsersID) {
        MessageModel messageModel = new MessageModel();

        if(legalCompanies == null || legalCompanies.length == 0){
            messageModel.setCode(0);
            messageModel.setMsg("未选中任何企业，请重新审批！");

            return messageModel;
        }

        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        for(int i = 0; i<projectUsersID.length; i++){
            User u = userMapper.queryUserByUserID(projectUsersID[i]);
            if(u != null){
//                System.out.println(u.getId());
                u.setProjectIn(1);
                userMapper.updateProjectIn(u);
            }
            session.commit();
        }
        messageModel.setMsg("审批完成！");
        return messageModel;
    }
}
