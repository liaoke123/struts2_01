package com.ssii.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminInfo {
    private Integer id;
    private String adminCode;
    private String password;
    private String name;
    private String telephone;
    private String email;
    private Date enrollDate;
    private String[] roleIds;

    //便于处理数据库查询结果时,快速的将结果转换成对象.
    public static AdminInfo toAdminInfo(ResultSet rs) throws SQLException {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setId(rs.getInt(1));
        adminInfo.setAdminCode(rs.getString(2));
        adminInfo.setPassword(rs.getString(3));
        adminInfo.setName(rs.getString(4));
        adminInfo.setTelephone(rs.getString(5));
        adminInfo.setEmail(rs.getString(6));
        adminInfo.setEnrollDate(rs.getDate(7));
        return adminInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
