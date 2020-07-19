package test;

import dao.BaseDao;
import util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//测试类
public class DataTest {

    public static void main(String[] args) {
        try {
            BaseDao b=new BaseDao(DatabaseUtil.getConnection());
            ResultSet resultSet = b.executeQuery("SELECT * FROM `easybuy_news`;");
            if(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
