package dxhualuo.database.codebuilder;
import dxhualuo.database.basedao.Database;
import dxhualuo.database.basedao.MySqlC3p0Impl;
import dxhualuo.database.basedao.MySqlImpl;

public class MySQL_CodeBuilder{
    private Database bd;
    public MySQL_CodeBuilder(String url, int port, String database, String username, String password) throws Exception {
        bd = new MySqlImpl(url, port, database, username, password);
    }
    public MySQL_CodeBuilder(String url, String database, String username, String password) throws Exception {
        bd = new MySqlImpl(url, database, username, password);
    }
    public MySQL_CodeBuilder(String connectionPoolName) throws Exception {
        bd = new MySqlC3p0Impl(connectionPoolName);
    }
}
