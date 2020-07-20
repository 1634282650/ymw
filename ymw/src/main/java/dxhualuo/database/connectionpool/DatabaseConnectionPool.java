package dxhualuo.database.connectionpool;

import dxhualuo.database.basedao.Database;

import java.sql.Connection;

public interface DatabaseConnectionPool {
    Connection getConnection();
    Connection getConnection(String config_Name);
    Database getDAO() throws Exception;
    Database getDAO(String config_Name);
}
