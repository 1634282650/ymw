package dao;

import dxhualuo.io.Path;
import dxhualuo.io.PropertiesManager;

import java.io.IOException;

/**
 *  数据库配置
 * @author dx_hualuo
 */
public class DatabaseConfig {
    public static String url;
    public static int port;
    public static String database;
    public static String userName;
    public static String password;
    static {
        try {
            PropertiesManager config = new PropertiesManager(Path.getSrcPathFile("database.properties"));
            url = config.getValue("shortUrl");
            port = Integer.parseInt(config.getValue("port"));
            database = config.getValue("database");
            userName = config.getValue("user");
            password = config.getValue("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
