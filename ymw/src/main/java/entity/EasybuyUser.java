package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 徐英杰
 */ //用户类
public class EasybuyUser {
    private Integer id;//主键
    private String loginName;//登录名
    private String userName;//用户名
    private String password;//密码
    private Integer sex;//性别(1:男 0：女)
    private String identityCode;//身份证号
    private String email;//邮箱
    private String mobile;//手机
    private Integer type;//类型（1：后台 0:前台）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    /**
     *  已经调用过next方法的ResultSet可以通过此方法来初始化一个实体并赋值
     * @param rs 结果集
     * @return 实体类对象
     * @author dx_hualuo
     */
    public static EasybuyUser readResultSet(ResultSet rs){
        EasybuyUser user = new EasybuyUser();
        try {
            user.setId(rs.getInt("id"));
            user.setLoginName(rs.getString("loginName"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setSex(rs.getInt("sex"));
            user.setIdentityCode(rs.getString("identityCode"));
            user.setEmail(rs.getString("email"));
            user.setMobile(rs.getString("mobile"));
            user.setType(rs.getInt("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
