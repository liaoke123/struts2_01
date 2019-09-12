package com.ssii.dao;

import com.ssii.pojo.AdminInfo;
import com.ssii.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//查询管理员
public class UserDao {
    private static final String sql = "select id,admin_code,password,name," +
            "telephone,email,erolldate from admin_info\n" +
            "where admin_code=? and password=?";

    //查询方法
    public static AdminInfo findByCodeAndPwd(String adminCode, String pwd) {
        //申明一些对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库的连接
            conn = ConnectionUtils.getConnection();
            //使用连接创建preStatement对象
            ps = conn.prepareStatement(sql);
            //执行查询
            ps.setString(1, adminCode);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            AdminInfo adminInfo = null;
            if (rs.next()) {
                //处理结果
                adminInfo = AdminInfo.toAdminInfo(rs);
            }
            return adminInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("e");
        } finally {
            //关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
