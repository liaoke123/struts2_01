package com.ssii.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    //静态代码块
    static {
        Properties props = new Properties();
        try {
            props.load(ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            //从数据库配置文件中获取我们想要的内容
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            //注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //获取数据库的连接
    public static Connection getConnection() throws SQLException {
        //首先从与当前线程绑定的ThreadLocal中获取连接
        Connection conn = tl.get();
        //判断是否获取到
        if (conn == null) {
            //没能获取到数据库连接,则通过驱动管理器获取
             conn = DriverManager.getConnection(url, username, password);
            //将获取到的连接存放到与当前线程绑定的threadlocal中
            tl.set(conn);
        }


        //将数据库连接返回
        return conn;
    }

    //关闭数据库连接
    public static void closeConnection(){
        //获取数据库连接
        Connection conn = tl.get();
        //判断是否获取到数据库连接
        //表明当前存在数据库连接,需要关闭连接
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //将threadlocal置空
        tl.set(null);
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
    }
}
