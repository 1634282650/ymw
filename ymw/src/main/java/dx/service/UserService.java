package dx.service;

import entity.EasybuyUser;
import dx.database.DataCache;
import dx.database.entity.EasyBuy;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dx_hualuo
 */
public class UserService {
    public static EasybuyUser login(String userName, String password){
        EasybuyUser user = null;
        try {
            ResultSet rs = EasyBuy.getDatabase().executeQuery("SELECT * FROM `easybuy_user` WHERE loginName='?' AND `password`='?'");
            if (rs.next()) {
                user = EasybuyUser.readResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean checkUserName(){
        return false;
    }

}
