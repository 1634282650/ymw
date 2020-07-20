package dxhualuo.database.basedao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author dx_hualuo
 */
public interface Database extends Serializable {
    /**
     *      获得数据库连接
     * @return 返回Connection
     * @throws SQLException 数据库连接时可能发生的异常
     */
    Connection getConnection() throws SQLException;

    /**
     *      可以一键关闭和数据库有关的对象
     * @param close 数据库connection，statement等等
     */
    default void closeAll(Object... close){
        for (Object obj: close) {
            if(obj != null){
                if(Connection.class.isAssignableFrom(obj.getClass())){
                    try { ((Connection)obj).close(); } catch (SQLException e) { e.printStackTrace(); }
                }
                else if(Statement.class.isAssignableFrom(obj.getClass())){
                    try { ((Statement)obj).close(); } catch (SQLException e) { e.printStackTrace(); }
                }
                else if(ResultSet.class.isAssignableFrom(obj.getClass())){
                    try { ((ResultSet)obj).close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }
        }
    }
    /**
     *      执行更新
     * @param preparedSql 更新用的sql语句
     * @param param 参数(可多个用逗号分开)
     * @return 返回受影响的行数
     * @throws SQLException 执行语句可能发生的异常
     */
    int executeUpdate(String preparedSql, Object... param) throws SQLException;
    /**
     *      执行查询
     * @param preparedSql 查询用的sql语句
     * @param param 参数(可多个用逗号分开)
     * @return  ResultSet结果
     * @throws SQLException 执行语句可能发生的异常
     */
    ResultSet executeQuery(String preparedSql, Object... param) throws SQLException;

    /**
     *      获取数据库类型
     * @return 返回数据库产品名
     */
    String getDataBaseType();
}
