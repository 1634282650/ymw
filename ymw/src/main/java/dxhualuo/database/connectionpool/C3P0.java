package dxhualuo.database.connectionpool;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import dxhualuo.database.basedao.Database;
import dxhualuo.database.basedao.MySqlC3p0Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author dx_hualuo
 */
public class C3P0 implements DatabaseConnectionPool {
    /**
     *      连接池的池
     */
    private HashMap<String, ComboPooledDataSource> dataSource;
    /**
     *      默认连接池
     */
    private ComboPooledDataSource defaultDs;
    /**
     *      私有构造，避免创建对象
     */
    private C3P0(){ }
    private static C3P0 thisC3P0;
    public static C3P0 getC3P0(){
        if(thisC3P0 == null){
            thisC3P0 = new C3P0();
        }
        return thisC3P0;
    }
    /**
     *      从C3P0连接池内获取连接
     * @param config_Name 连接配置名字
     * @return Connection数据库连接对象
     */
    @Override
    public Connection getConnection(String config_Name) {
        ComboPooledDataSource ds = this.getComboPooledDataSource(config_Name);
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("获取连接失败");
        }
        return connection;
    }
    /**
     *      从C3P0默认配置获取连接
     * @return Connection数据库连接对象
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = this.getComboPooledDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("获取连接失败");
        }
        return connection;
    }

    /**
     *      获得默认C3P0连接池
     * @return  ComboPooledDataSource
     */
    public ComboPooledDataSource getComboPooledDataSource(){
        if(defaultDs == null){
            defaultDs = new ComboPooledDataSource();
        }
        return defaultDs;
    }

    /**
     *      通过指定配置名称，获得默认C3P0连接池
     * @param config_name 配置名称
     * @return  ComboPooledDataSource
     */
    public ComboPooledDataSource getComboPooledDataSource(String config_name){
        ComboPooledDataSource ds;
        if(dataSource == null){
            dataSource = new HashMap<>();
            ds = new ComboPooledDataSource(config_name);
            dataSource.put(config_name, ds);
            return ds;
        }
        ds = dataSource.get(config_name);
        if(ds == null){
            ds = new ComboPooledDataSource(config_name);
            dataSource.put(config_name, ds);
        }
        return ds;
    }
    @Override
    public Database getDAO() throws Exception { return new MySqlC3p0Impl(); }
    @Override
    public Database getDAO(String config){ return new MySqlC3p0Impl().setC3P0(config); }
}
