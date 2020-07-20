package dxhualuo.database.paging;

import dxhualuo.database.basedao.Database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dx_hualuo
 */
public class Pagers implements Serializable {
    /**
     * 查询数据SQL语句
     */
    private String queryDataSql;
    /**
     * 查询数量SQL语句
     */
    private String queryCountSql;
    /**
     * 数据库连接对象
     */
    private Database dataBase;
    /**
     * 页面配置信息
     */
    private int pageSize = 0;
    private Integer pageCount;
    private Integer dataCount;

    /**
     *      通过数据库创建分页器
     * @param database  数据库DAO
     * @param queryDataSql 查询SQl语句
     */
    public Pagers(Database database, String queryDataSql){
        this.dataBase = database;
        this.queryDataSql = queryDataSql;
    }

    /**
     *  获得分页总页数
     * @return 页数
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        computePageCount();
    }

    public int getPageCount() {
        if(pageCount == null){
            computePageCount();
        }
        return pageCount;
    }
    public int getDataCount() throws SQLException{
        Connection con;
        if(dataCount == null){
            con = dataBase.getConnection();
            PreparedStatement ps = con.prepareStatement(getQueryCountSql());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                dataCount = rs.getInt("Count");
            }
            rs.close();
            con.close();
        }
        return dataCount;
    }
    private void computePageCount(){
        if(dataCount != null){
            pageCount = dataCount % pageSize == 0 ? dataCount / pageSize : (dataCount / pageSize) + 1;
        }
    }

    public String getQueryCountSql() {
        if(queryCountSql == null){
            if(queryDataSql != null){
                queryCountSql = "SELECT COUNT(0) AS 'Count' FROM ("+queryDataSql+") AS result";
            }
        }
        return queryCountSql;
    }

    public void setQueryCountSql(String queryCountSql) {
        this.queryCountSql = queryCountSql;
    }

    public String getQueryDataSql() {
        return queryDataSql;
    }

    public void setQueryDataSql(String queryDataSql) {
        this.queryDataSql = queryDataSql;
    }
}
