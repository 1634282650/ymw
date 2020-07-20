package dxhualuo.database;

import java.util.HashSet;

/**
 *  数据库驱动信息
 * @author dx_hualuo
 */
public class DatabaseDriverInfo {
    private static HashSet<String> init = new HashSet<>();

    /**
     *      获取该数据库类型是否已经载入驱动
     * @param databaseName 数据库名字
     * @return 是否载入驱动，布尔值
     */
    public static boolean isInit(String databaseName){
        return init.contains(databaseName.toUpperCase());
    }

    /**
     *      设置databaseName类型的数据库，已经被初始化
     * @param databaseName 已经初始化了的数据库名字
     */
    public static void init(String databaseName){
        init.add(databaseName.toUpperCase());
    }
}
