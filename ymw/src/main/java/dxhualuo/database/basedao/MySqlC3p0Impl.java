package dxhualuo.database.basedao;

import dxhualuo.database.ResultSetPlus;
import dxhualuo.database.connectionpool.C3P0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 17868
 */
public class MySqlC3p0Impl implements Database {
    private String c3P0ConfigName;
    public MySqlC3p0Impl(String configName) {
        c3P0ConfigName = configName;
    }
    public MySqlC3p0Impl(){}
    @Override
    public Connection getConnection() {
        if(c3P0ConfigName == null){
            return C3P0.getC3P0().getConnection();
        }else{
            return C3P0.getC3P0().getConnection(c3P0ConfigName);
        }
    }

    /**
     *      执行更新SQL语句所用方法
     * @param sql SQL语句
     * @param par   参数列表
     * @return  受影响函数
     * @throws SQLException 各种SQL更新异常
     */
    @Override
    public int executeUpdate(String sql, Object... par) throws SQLException {
        int returnResult = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < par.length; i++) {
                preparedStatement.setObject(i+1, par[i]);
            }
            returnResult = preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
        return returnResult;
    }

    /**
     *      执行查询SQL语句
     * @param sql SQL语句
     * @param par 参数列表
     * @return  ResultSet结果集
     * @throws SQLException 各种SQL查询异常
     */
    @Override
    public ResultSet executeQuery(String sql, Object... par) throws SQLException {
        ResultSet returnResult = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < par.length; i++) {
                preparedStatement.setObject(i+1, par[i]);
            }
            returnResult = preparedStatement.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return new ResultSetPlus(returnResult, connection);
    }

    @Override
    public String getDataBaseType() {
        return "MySQL";
    }

    /**
     *      设置数据库连接来自于C3P0
     * @param config_name   配置名
     */
    public Database setC3P0(String config_name){
        this.c3P0ConfigName = config_name;
        return this;
    }
    /**
     *      设置数据库连接来自于C3P0
     */
    public Database setC3P0(){
        return this;
    }
}
