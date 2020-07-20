package dx.database.entity;
import dao.DatabaseConfig;
import dxhualuo.database.basedao.Database;
import dxhualuo.database.basedao.MySqlImpl;

/**
 *  数据库实体
 * @author dx_hualuo
 */
public class EasyBuy {
    private static MySqlImpl mySql;
    static {
        try {
            mySql = new MySqlImpl(DatabaseConfig.url, DatabaseConfig.port, DatabaseConfig.database, DatabaseConfig.userName, DatabaseConfig.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  获得数据库
     * @return 数据库
     */
    public static Database getDatabase(){
        return mySql;
    }
}
