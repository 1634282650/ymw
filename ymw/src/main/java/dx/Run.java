package dx;

import dx.database.entity.EasyBuy;
import dxhualuo.database.basedao.Database;

import java.sql.ResultSet;

/**
 * @author dx_hualuo
 */
public class Run {
    public static void main(String[] args) throws Exception {
        Database db = EasyBuy.getDatabase();
        ResultSet rs = db.executeQuery("SELECT id FROM easybuy_product_category WHERE `name`='手机'");
        System.out.println(rs.next());
    }
}
