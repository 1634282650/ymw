package dx.service;

import entity.EasybuyUser;
import dx.database.entity.EasyBuy;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  User的Service
 * @author dx_hualuo
 */
public class UserService {
    /**
     *  登录，如果登陆成功返回实体类，如果登陆失败返回null。
     * @param userName 用户名
     * @param password 密码
     * @return User实体类
     */
    public static EasybuyUser login(String userName, String password){
        EasybuyUser user = null;
        try {
            ResultSet rs = EasyBuy.getDatabase().executeQuery("SELECT * FROM `easybuy_user` WHERE loginName=? AND `password`=?", userName, password);
            int i = 0;
            while(rs.next()){
                if(i > 0){
                    throw new RuntimeException("请检查数据库，用户名："+userName+"，存在重名用户！");
                }
                user = EasybuyUser.readResultSet(rs);
                i++;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  检查用户是否存在
     * @return 是否存在
     */
    public static boolean checkUserName(String userName){
        try {
            ResultSet rs = EasyBuy.getDatabase().executeQuery("SELECT * FROM `easybuy_user` WHERE loginName=?", userName);
            int i = 0;
            while(rs.next()){
                if(i > 0){
                    throw new RuntimeException("请检查数据库，用户名："+userName+"，存在重名用户！");
                }
                EasybuyUser.readResultSet(rs);
                i++;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  添加用户
     * @param user User的实体类对象
     * @return 是否添加成功
     */
    public static boolean addUser(EasybuyUser user){
        if(user == null){
            throw new NullPointerException();
        }
        try {
            int i = EasyBuy.getDatabase().executeUpdate("INSERT easybuy_user(loginName,`password`,userName,sex,identityCode,email,mobile) VALUES(?,?,?,?,?,?,?)",
                    user.getLoginName(), user.getPassword(), user.getUserName(), user.getSex(), user.getIdentityCode(), user.getEmail(), user.getMobile());
            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
