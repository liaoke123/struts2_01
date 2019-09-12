package com.ssii.dao;

import com.ssii.pojo.Fee;
import com.ssii.utils.ConnectionUtils;
import com.ssii.utils.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//查询数据库,获取数据,封装
public class FeeDao {
    //定义一个查询语句
    private static final String sql = "select id, name, base_duration, base_cost, unit_cost, creatime, " +
            "startime, status, descr from cost";
    private static final String totalRowNum = "select count(*) from cost";
    private static final String findByName = "select id,name,base_duration,base_cost," +
            "unit_cost,creatime,startime from cost where name=?";
    private static final String findById = "select id,name,base_duration,base_cost," +
            "unit_cost,creatime,startime from cost where id=?";
    private static final String deleteById = "delete from cost where id=?";
    private static final String update = "update cost set name=?,base_duration=?," +
            "base_cost=?,unit_cost=? where id=?";

    /*查询所有*/
    public static List<Fee> findAll(){
        List<Fee> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            conn =  ConnectionUtils.getConnection();
            //生成preStatement对象
            ps = conn.prepareStatement(sql);
            //查询数据库
            ResultSet rs = ps.executeQuery();
            //处理查询结果
            while (rs.next()) {
                Fee fee = Fee.toFee(rs);
                list.add(fee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            ConnectionUtils.closeConnection();
        }
        return list;
    }

    /*更新 */
    public void update(Fee fee){
        //获取会话对象
        Session session = HibernateUtils.getSession();
        //开启事务
        Transaction tx = session.beginTransaction();
        //操作对象
        fee.setCreateTime(new Date(System.currentTimeMillis()));
        session.update(fee);
        //提交事务
        tx.commit();
        //关闭会话
        session.close();
    }

    /*
    根据id获取数据
     */
    public Fee getById(Integer id){
        //获取会话
        Session session = HibernateUtils.getSession();
        Fee fee = session.get(Fee.class, id);
        //关闭会话
        session.close();
        //返回查询结果
        return fee;
    }

    /*
    根据名字获取数据
     */
    public Fee getByName(String name){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            conn = ConnectionUtils.getConnection();
            //获取执行对象
            ps = conn.prepareStatement(findByName);
            //设置参数
            ps.setString(1,name);
            //执行查询
            rs = ps.executeQuery();
            //处理结果,封装数据
            Fee fee = null;
            if (rs.next()) {
                fee = Fee.toFee(rs);
            }
            return fee;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            ConnectionUtils.closeConnection();
        }
    }

    /*
    根据id进行删除
     */
    public void deleteById(Integer id){
        //获取会话
        Session session = HibernateUtils.getSession();
        Fee fee = new Fee();
        //开启事务
        Transaction tx = session.beginTransaction();
        fee.setId(id);
        session.delete(fee);
        //提交事务
        tx.commit();
        //关闭会话
        session.close();
    }


    /*
    获取总页数
     */
    public int getTotalPages(int pageSize){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalPage = 0;
        try {
            //获取数据库连接
            conn = ConnectionUtils.getConnection();
            //创建执行对象
            ps = conn.prepareStatement(totalRowNum);
            //执行查询
            rs = ps.executeQuery();
            //处理结果,获取里面的数据
            if(rs.next()){
                int rowNum = rs.getInt(1);
                //进行判断,然后计算页数
                if(rowNum % pageSize == 0){
                    totalPage = (rowNum % pageSize);
                }else {
                    totalPage = (rowNum % pageSize) + 1;
                }
            }
            return totalPage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionUtils.closeConnection();
        }
    }



}
