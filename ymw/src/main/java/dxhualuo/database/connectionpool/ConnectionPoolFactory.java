package dxhualuo.database.connectionpool;

import dxhualuo.reflect.ReflectClass;
import dxhualuo.reflect.ReflectMethod;

public class ConnectionPoolFactory {
    public static DatabaseConnectionPool getConnectionPool(String connectionPoolName) throws Exception {
        if("ConnectionPoolFactory".equals(connectionPoolName) || "DatabaseConnectionPool".equals(connectionPoolName)){
            throw new ClassNotFoundException();
        }
        ReflectClass aClass = new ReflectClass("dx_hualuo.util.database.connectionPool." + connectionPoolName);
        ReflectMethod method = aClass.StaticMethod("get" + connectionPoolName);
        Object obj = method.execute();
        if(ReflectClass.isExtendOrImplementFrom(aClass.toClass(), DatabaseConnectionPool.class)){
            return (DatabaseConnectionPool)obj;
        }
        throw new Exception("目标数据库连接池错误，暂时无法使用！请联系管理员检修！");
    }
}
