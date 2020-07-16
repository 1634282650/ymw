

import dao.BaseDao;
import util.DatabaseUtil;

import java.sql.SQLException;

public class t {

    public static void main(String[] args) throws SQLException {
        BaseDao b=new BaseDao(DatabaseUtil.getConnection());
        try {
            b.executeUpdate("UPDATE `easybuy_news` SET `content`='1111111会员特惠月开始了1'\n" +
                    "\n" +
                    "WHERE id=?;",597);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
