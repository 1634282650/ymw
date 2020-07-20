package dxhualuo.database.basedao;

import dxhualuo.database.DatabaseDriverInfo;
import dxhualuo.database.ResultSetPlus;

import java.sql.*;

public class MySqlImpl implements Database {
    /**
     *  数据库连接字符串，后方所接参数
     */
    private final static String URL_PARAMETER = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=utf-8";
    protected String database;
    protected String url;
    protected Integer port;
    protected String userName;
    protected String password;
    protected static String[] driver = {"com.mysql.cj.jdbc.Driver","com.mysql.jdbc.Driver"};
    /**
     *      创建DAO对象
     * @param url   数据库服务器地址
     * @param database  连接的数据库
     * @param username  用户名
     * @param password    密码
     * @throws Exception 没有导入MySQL连接器会抛出异常
     */
    public MySqlImpl(String url, String database, String username, String password) throws Exception{
        boolean isLoad = DatabaseDriverInfo.isInit("MySQL");
        if(isLoad){

        }
        //自动载入驱动
        for (String s : driver) {
            try {
                Class.forName(s);
                isLoad = true;
                break;
            } catch (Exception ignored) { }
        }
        //判断驱动是否被载入
        if(!isLoad){
            //抛出未载入异常
            throw new Exception("MySQL连接器Jar包未导入，找不到驱动类！");
        }
        this.url = url;
        this.userName = username;
        this.password = password;
        this.database = database;
    }
    /**
     *      创建DAO对象
     * @param url   数据库服务器地址
     * @param port  数据库连接端口
     * @param database  连接的数据库
     * @param username  用户名
     * @param password    密码
     * @throws Exception 没有导入MySQL连接器会抛出异常
     */
    public MySqlImpl(String url, int port, String database, String username, String password) throws Exception{
        boolean isLoad = false;
        //自动载入驱动
        for (String s : driver) {
            try {
                Class.forName(s);
                isLoad = true;
                break;
            } catch (Exception ignored) { }
        }
        //判断驱动是否被载入
        if(!isLoad){
            //抛出未载入异常
            throw new Exception("MySQL连接器Jar包未导入，找不到驱动类！");
        }
        this.url = url;
        this.userName = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    public MySqlImpl() throws Exception {
        boolean isLoad = false;
        //自动载入驱动
        for (String s : driver) {
            try {
                Class.forName(s);
                isLoad = true;
                break;
            } catch (Exception ignored) { }
        }
        //判断驱动是否被载入
        if(!isLoad){
            //抛出未载入异常
            throw new Exception("MySQL连接器Jar包未导入，找不到驱动类！");
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
     *      获得SQL链接
     * @return  Connection
     * @throws SQLException 各种异常
     */
    @Override
    public Connection getConnection() throws SQLException {
        if(port != null){
            return DriverManager.getConnection("jdbc:mysql://"+ url+":"+ port.toString()+ "/"+ database+ URL_PARAMETER, userName, password);
        }else{
            return DriverManager.getConnection("jdbc:mysql://"+ url+ "/"+ database+ URL_PARAMETER, userName, password);
        }
    }
}
